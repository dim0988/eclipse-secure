package com.eclipse.secure

import android.app.Activity
import android.net.VpnService
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.PowerSettingsNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wireguard.android.backend.GoBackend
import com.wireguard.android.backend.Tunnel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val BgDark = Color(0xFF0A0612)
val CardDark = Color(0xFF140D24)
val NeonPurple = Color(0xFF9D4EDD)
val NeonGlow = Color(0xFFC77DFF)
val ConnectedGreen = Color(0xFF00F5D4)

class EclipseTunnel : Tunnel {
    override fun getName(): String = "eclipse_wg0"
    override fun onStateChange(newState: Tunnel.State) {}
}

class MainActivity : ComponentActivity() {
    private lateinit var backend: GoBackend
    private val tunnel = EclipseTunnel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backend = GoBackend(applicationContext)

        setContent {
            EclipseSecureScreen(backend = backend, tunnel = tunnel)
        }
    }
}

@Composable
fun EclipseSecureScreen(backend: GoBackend, tunnel: EclipseTunnel) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var selectedServer by remember { mutableStateOf(ServerRepository.servers.first()) }
    var isConnected by remember { mutableStateOf(false) }
    var isConnecting by remember { mutableStateOf(false) }

    fun toggleTunnel() {
        scope.launch {
            isConnecting = true
            try {
                if (isConnected) {
                    withContext(Dispatchers.IO) {
                        backend.setState(tunnel, Tunnel.State.DOWN, null)
                    }
                    isConnected = false
                } else {
                    val config = ServerRepository.buildWireGuardConfig(selectedServer)
                    withContext(Dispatchers.IO) {
                        backend.setState(tunnel, Tunnel.State.UP, config)
                    }
                    isConnected = true
                }
            } catch (e: Exception) {
                isConnected = false
                Toast.makeText(context, "Ошибка туннеля: ${e.message}", Toast.LENGTH_LONG).show()
            } finally {
                isConnecting = false
            }
        }
    }

    val vpnPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            toggleTunnel()
        } else {
            Toast.makeText(context, "Разрешение VPN отклонено", Toast.LENGTH_SHORT).show()
        }
    }

    val ringColor by animateColorAsState(
        targetValue = if (isConnected) ConnectedGreen else NeonPurple,
        label = "ring"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(BgDark, Color(0xFF120826), BgDark)))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "ECLIPSE SECURE",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 3.sp
        )
        Text(
            text = "WIREGUARD ENCRYPTED TUNNEL",
            color = NeonGlow,
            fontSize = 11.sp,
            letterSpacing = 1.5.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(180.dp)
                .shadow(24.dp, CircleShape, spotColor = ringColor, ambientColor = ringColor)
                .clip(CircleShape)
                .background(CardDark)
                .border(3.dp, ringColor, CircleShape)
                .clickable(enabled = !isConnecting) {
                    val intent = VpnService.prepare(context)
                    if (intent != null && !isConnected) {
                        vpnPermissionLauncher.launch(intent)
                    } else {
                        toggleTunnel()
                    }
                }
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Rounded.PowerSettingsNew,
                    contentDescription = "Connect",
                    tint = ringColor,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = when {
                        isConnecting -> "CONNECTING..."
                        isConnected -> "CONNECTED"
                        else -> "DISCONNECTED"
                    },
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Rounded.Lock,
                contentDescription = null,
                tint = if (isConnected) ConnectedGreen else Color.Gray,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = if (isConnected) "Protected • ${selectedServer.country}" else "Traffic Unprotected",
                color = if (isConnected) ConnectedGreen else Color.Gray,
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "SELECT LOCATION",
            color = Color.Gray,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.5.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        ServerRepository.servers.forEach { server ->
            val isSelected = server.id == selectedServer.id
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(CardDark)
                    .border(
                        width = if (isSelected) 1.5.dp else 1.dp,
                        color = if (isSelected) NeonGlow else Color(0xFF261942),
                        shape = RoundedCornerShape(14.dp)
                    )
                    .clickable(enabled = !isConnected && !isConnecting) {
                        selectedServer = server
                    }
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                Text(text = server.flag, fontSize = 22.sp)
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = server.country,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(1f)
                )
                RadioButton(
                    selected = isSelected,
                    onClick = null,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = NeonGlow,
                        unselectedColor = Color.Gray
                    )
                )
            }
        }
    }
}
