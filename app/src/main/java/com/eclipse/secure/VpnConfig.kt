package com.eclipse.secure

import com.wireguard.config.Config
import java.io.BufferedReader
import java.io.StringReader

data class ServerNode(
    val id: String,
    val country: String,
    val flag: String,
    val endpoint: String,
    val publicKey: String,
    val privateKey: String,
    val clientAddress: String,
    val mtu: Int = 1280
)

object ServerRepository {
    val servers = listOf(
        ServerNode(
            id = "warp_main",
            country = "Cloudflare WARP (Auto)",
            flag = "⚡",
            endpoint = "162.159.192.1:2408",
            publicKey = "bmXOC+F1FxEMF9dyiK2H5/1SUtzH0JuVo51h2wPfgyo=",
            privateKey = "+EZB5TIkusn0wC90jlQ/s+WqQ7XsGeMr02t14WFCgnw=",
            clientAddress = "172.16.0.2/32, 2606:4700:110:8e8e:8fab:8405:be2a:5f0/128",
            mtu = 1280
        ),
        ServerNode(
            id = "warp_alt",
            country = "Cloudflare WARP (Port 500)",
            flag = "🛡️",
            endpoint = "162.159.193.10:500",
            publicKey = "bmXOC+F1FxEMF9dyiK2H5/1SUtzH0JuVo51h2wPfgyo=",
            privateKey = "+EZB5TIkusn0wC90jlQ/s+WqQ7XsGeMr02t14WFCgnw=",
            clientAddress = "172.16.0.2/32, 2606:4700:110:8e8e:8fab:8405:be2a:5f0/128",
            mtu = 1280
        )
    )

    fun buildWireGuardConfig(node: ServerNode): Config {
        val rawConfig = """
            [Interface]
            PrivateKey = ${node.privateKey}
            Address = ${node.clientAddress}
            DNS = 1.1.1.1, 1.0.0.1
            MTU = ${node.mtu}

            [Peer]
            PublicKey = ${node.publicKey}
            Endpoint = ${node.endpoint}
            AllowedIPs = 0.0.0.0/0, ::/0
            PersistentKeepalive = 25
        """.trimIndent()

        return Config.parse(BufferedReader(StringReader(rawConfig)))
    }
}
