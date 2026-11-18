package com.eclipse.secure

import org.amnezia.awg.config.Config
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
            id = "awg_warp_1",
            country = "AmneziaWG • WARP 1",
            flag = "⚡",
            endpoint = "162.159.192.1:2408",
            publicKey = "bmXOC+F1FxEMF9dyiK2H5/1SUtzH0JuVo51h2wPfgyo=",
            privateKey = "+EZB5TIkusn0wC90jlQ/s+WqQ7XsGeMr02t14WFCgnw=",
            clientAddress = "172.16.0.2/32",
            mtu = 1280
        ),
        ServerNode(
            id = "awg_warp_2",
            country = "AmneziaWG • WARP 2",
            flag = "🛡️",
            endpoint = "162.159.193.10:500",
            publicKey = "bmXOC+F1FxEMF9dyiK2H5/1SUtzH0JuVo51h2wPfgyo=",
            privateKey = "+EZB5TIkusn0wC90jlQ/s+WqQ7XsGeMr02t14WFCgnw=",
            clientAddress = "172.16.0.2/32",
            mtu = 1280
        )
    )

    fun buildWireGuardConfig(node: ServerNode): Config {
        val rawConfig = """
            [Interface]
            PrivateKey = ${node.privateKey}
            Address = ${node.clientAddress}
            DNS = 1.1.1.1, 8.8.8.8
            MTU = ${node.mtu}
            ExcludedApplications = com.eclipse.secure
            Jc = 5
            Jmin = 50
            Jmax = 1000
            S1 = 0
            S2 = 0
            H1 = 1
            H2 = 2
            H3 = 3
            H4 = 4

            [Peer]
            PublicKey = ${node.publicKey}
            Endpoint = ${node.endpoint}
            AllowedIPs = 0.0.0.0/0
            PersistentKeepalive = 25
        """.trimIndent()

        return Config.parse(BufferedReader(StringReader(rawConfig)))
    }
}
