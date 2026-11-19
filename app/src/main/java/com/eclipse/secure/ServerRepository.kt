package com.eclipse.secure

data class ServerModel(
    val id: String,
    val country: String,
    val flag: String,
    val address: String,
    val pubKey: String,
    val endpoint: String
)

object ServerRepository {
    val servers = listOf(
        ServerModel(
            id = "srv-01",
            country = "Germany (Frankfurt)",
            flag = "🇩🇪",
            address = "10.0.0.2/32",
            pubKey = "dummy_pub_key_1=",
            endpoint = "151.245.192.228:443"
        ),
        ServerModel(
            id = "srv-02",
            country = "Netherlands (Amsterdam)",
            flag = "🇳🇱",
            address = "10.0.0.3/32",
            pubKey = "dummy_pub_key_2=",
            endpoint = "151.245.192.230:443"
        )
    )

    fun buildWireGuardConfig(server: ServerModel): String {
        return """
            [Interface]
            PrivateKey = aaaaaaaA/1111111111111111111111111111111111=
            Address = ${server.address}
            
            [Peer]
            PublicKey = ${server.pubKey}
            Endpoint = ${server.endpoint}
            AllowedIPs = 0.0.0.0/0
        """.trimIndent()
    }
}
