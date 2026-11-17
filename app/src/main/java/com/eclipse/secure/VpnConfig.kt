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
    val clientAddress: String
)

object ServerRepository {
    val servers = listOf(
        ServerNode(
            id = "de",
            country = "Germany",
            flag = "🇩🇪",
            endpoint = "185.220.101.1:51820",
            publicKey = "xTIBA5rboUvnH4htodjb6e697QjLERt1NAB4mZqp8Dg=",
            privateKey = "yAnz5TF+lXXJte14tji3zlMNq+hd2rYUIgJBgB3fBmk=",
            clientAddress = "10.66.66.2/32"
        ),
        ServerNode(
            id = "us",
            country = "USA",
            flag = "🇺🇸",
            endpoint = "198.51.100.10:51820",
            publicKey = "xTIBA5rboUvnH4htodjb6e697QjLERt1NAB4mZqp8Dg=",
            privateKey = "yAnz5TF+lXXJte14tji3zlMNq+hd2rYUIgJBgB3fBmk=",
            clientAddress = "10.66.66.3/32"
        ),
        ServerNode(
            id = "uk",
            country = "UK",
            flag = "🇬🇧",
            endpoint = "203.0.113.25:51820",
            publicKey = "xTIBA5rboUvnH4htodjb6e697QjLERt1NAB4mZqp8Dg=",
            privateKey = "yAnz5TF+lXXJte14tji3zlMNq+hd2rYUIgJBgB3fBmk=",
            clientAddress = "10.66.66.4/32"
        ),
        ServerNode(
            id = "nl",
            country = "Netherlands",
            flag = "🇳🇱",
            endpoint = "192.0.2.44:51820",
            publicKey = "xTIBA5rboUvnH4htodjb6e697QjLERt1NAB4mZqp8Dg=",
            privateKey = "yAnz5TF+lXXJte14tji3zlMNq+hd2rYUIgJBgB3fBmk=",
            clientAddress = "10.66.66.5/32"
        )
    )

    fun buildWireGuardConfig(node: ServerNode): Config {
        val rawConfig = """
            [Interface]
            PrivateKey = ${node.privateKey}
            Address = ${node.clientAddress}
            DNS = 1.1.1.1, 9.9.9.9

            [Peer]
            PublicKey = ${node.publicKey}
            Endpoint = ${node.endpoint}
            AllowedIPs = 0.0.0.0/0, ::/0
            PersistentKeepalive = 25
        """.trimIndent()

        return Config.parse(BufferedReader(StringReader(rawConfig)))
    }
}
