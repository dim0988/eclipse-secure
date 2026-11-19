package com.eclipse.secure

object VpnConfig {
    val xrayConfigJson: String = """
    {
      "remarks": "⚡️ ECLIPSE SECURE",
      "meta": {
        "serverDescription": "Автовыбор сервера"
      },
      "log": {
        "loglevel": "none",
        "access": "none",
        "error": "none"
      },
      "policy": {
        "levels": {
          "0": {
            "handshake": 4,
            "connIdle": 1800,
            "uplinkOnly": 2,
            "downlinkOnly": 5,
            "bufferSize": 4
          }
        },
        "system": {
          "statsInboundUplink": false,
          "statsInboundDownlink": false,
          "statsOutboundUplink": false,
          "statsOutboundDownlink": false
        }
      },
      "dns": {
        "tag": "dns_in",
        "queryStrategy": "UseIPv4",
        "disableCache": false,
        "servers": [
          {
            "address": "https://77.88.8.8/dns-query",
            "domains": [
              "domain:ru",
              "domain:su",
              "domain:xn--p1ai",
              "domain:moscow",
              "domain:1tv.com",
              "domain:2gis.com",
              "domain:alfabank.com",
              "domain:alfabank.st",
              "domain:avito.com",
              "domain:avito.st",
              "domain:cian.site",
              "domain:clck.ly",
              "domain:ivi.tv",
              "domain:kinopoisk.com",
              "domain:kontur.host",
              "domain:megamarket.tech",
              "domain:moex.com",
              "domain:mycdn.me",
              "domain:naydex.net",
              "domain:ntv.tv",
              "domain:okko.tv",
              "domain:ozon.com",
              "domain:ozonusercontent.com",
              "domain:premier.one",
              "domain:rutube.me",
              "domain:sberbank.com",
              "domain:tank-online.com",
              "domain:tbank.com",
              "domain:tbank-online.com",
              "domain:tildaapi.com",
              "domain:tinkoff.com",
              "domain:userapi.com",
              "domain:vk.com",
              "domain:vk.me",
              "domain:vk-cdn.net",
              "domain:vk-portal.net",
              "domain:vkuser.net",
              "domain:vkuseraudio.net",
              "domain:vkuservideo.net",
              "domain:wbstatic.net",
              "domain:ya.cc",
              "domain:yandex.net",
              "domain:yandex.com",
              "domain:yandexcloud.net",
              "domain:yastatic.net",
              "full:go.yandex"
            ],
            "skipFallback": true
          },
          "https://8.8.8.8/dns-query",
          "localhost"
        ]
      },
      "inbounds": [
        {
          "listen": "127.0.0.1",
          "port": 10808,
          "protocol": "socks",
          "settings": {
            "auth": "noauth",
            "udp": true
          },
          "sniffing": {
            "destOverride": [
              "http",
              "tls",
              "quic"
            ],
            "enabled": true,
            "routeOnly": true
          },
          "tag": "socks"
        },
        {
          "listen": "127.0.0.1",
          "port": 10809,
          "protocol": "http",
          "settings": {
            "allowTransparent": false
          },
          "sniffing": {
            "destOverride": [
              "http",
              "tls",
              "quic"
            ],
            "enabled": true,
            "routeOnly": true
          },
          "tag": "http"
        }
      ],
      "observatory": {
        "enableConcurrency": false,
        "probeInterval": "2m",
        "probeUrl": "http://probe.malina24.xyz/generate_204",
        "subjectSelector": [
          "srv-01-ea4af111",
          "srv-02-ea4af111",
          "srv-03-ea4af111",
          "srv-04-ea4af111",
          "srv-05-ea4af111",
          "srv-06-ea4af111",
          "srv-07-ea4af111",
          "srv-08-ea4af111",
          "srv-09-ea4af111",
          "srv-10-ea4af111",
          "srv-11-ea4af111",
          "srv-12-ea4af111"
        ]
      },
      "outbounds": [
        {
          "protocol": "vless",
          "settings": {
            "vnext": [
              {
                "address": "151.245.192.228",
                "port": 443,
                "users": [
                  {
                    "encryption": "none",
                    "id": "f93777cf-98bd-4722-ad2d-8a37302e91e8"
                  }
                ]
              }
            ]
          },
          "streamSettings": {
            "network": "grpc",
            "grpcSettings": {
              "serviceName": "sync.v1",
              "multiMode": true
            },
            "security": "tls",
            "tlsSettings": {
              "allowInsecure": false,
              "serverName": "cugpbyxccy.j.cloudfront.hls.vodcdn-edge.net",
              "fingerprint": "safari",
              "show": false,
              "alpn": ["h2"]
            },
            "sockopt": {
              "tcpKeepAliveInterval": 15,
              "tcpKeepAliveIdle": 30,
              "tcpNoDelay": true
            }
          },
          "tag": "srv-01-ea4af111"
        },
        {
          "protocol": "vless",
          "settings": {
            "vnext": [
              {
                "address": "151.245.192.230",
                "port": 443,
                "users": [
                  {
                    "encryption": "none",
                    "id": "f93777cf-98bd-4722-ad2d-8a37302e91e8"
                  }
                ]
              }
            ]
          },
          "streamSettings": {
            "network": "grpc",
            "grpcSettings": {
              "serviceName": "sync.v1",
              "multiMode": true
            },
            "security": "tls",
            "tlsSettings": {
              "allowInsecure": false,
              "serverName": "cugpbyxccy.j.cloudfront.hls.vodcdn-edge.net",
              "fingerprint": "safari",
              "show": false,
              "alpn": ["h2"]
            },
            "sockopt": {
              "tcpKeepAliveInterval": 15,
              "tcpKeepAliveIdle": 30,
              "tcpNoDelay": true
            }
          },
          "tag": "srv-02-ea4af111"
        },
        {
          "protocol": "vless",
          "settings": {
            "vnext": [
              {
                "address": "151.245.192.197",
                "port": 443,
                "users": [
                  {
                    "encryption": "none",
                    "id": "f93777cf-98bd-4722-ad2d-8a37302e91e8"
                  }
                ]
              }
            ]
          },
          "streamSettings": {
            "network": "grpc",
            "grpcSettings": {
              "serviceName": "sync.v1",
              "multiMode": true
            },
            "security": "tls",
            "tlsSettings": {
              "allowInsecure": false,
              "serverName": "l71vtfjom2.j.cloudfront.hls.vodcdn-edge.net",
              "fingerprint": "safari",
              "show": false,
              "alpn": ["h2"]
            },
            "sockopt": {
              "tcpKeepAliveInterval": 15,
              "tcpKeepAliveIdle": 30,
              "tcpNoDelay": true
            }
          },
          "tag": "srv-03-ea4af111"
        },
        {
          "protocol": "vless",
          "settings": {
            "vnext": [
              {
                "address": "151.245.192.196",
                "port": 443,
                "users": [
                  {
                    "encryption": "none",
                    "id": "f93777cf-98bd-4722-ad2d-8a37302e91e8"
                  }
                ]
              }
            ]
          },
          "streamSettings": {
            "network": "grpc",
            "grpcSettings": {
              "serviceName": "sync.v1",
              "multiMode": true
            },
            "security": "tls",
            "tlsSettings": {
              "allowInsecure": false,
              "serverName": "l71vtfjom2.j.cloudfront.hls.vodcdn-edge.net",
              "fingerprint": "safari",
              "show": false,
              "alpn": ["h2"]
            },
            "sockopt": {
              "tcpKeepAliveInterval": 15,
              "tcpKeepAliveIdle": 30,
              "tcpNoDelay": true
            }
          },
          "tag": "srv-04-ea4af111"
        },
        {
          "protocol": "vless",
          "settings": {
            "vnext": [
              {
                "address": "151.245.192.229",
                "port": 443,
                "users": [
                  {
                    "encryption": "none",
                    "id": "f93777cf-98bd-4722-ad2d-8a37302e91e8"
                  }
                ]
              }
            ]
          },
          "streamSettings": {
            "network": "grpc",
            "grpcSettings": {
              "serviceName": "sync.v1",
              "multiMode": true
            },
            "security": "tls",
            "tlsSettings": {
              "allowInsecure": false,
              "serverName": "cugpbyxccy.j.cloudfront.hls.vodcdn-edge.net",
              "fingerprint": "safari",
              "show": false,
              "alpn": ["h2"]
            },
            "sockopt": {
              "tcpKeepAliveInterval": 15,
              "tcpKeepAliveIdle": 30,
              "tcpNoDelay": true
            }
          },
          "tag": "srv-05-ea4af111"
        },
        {
          "protocol": "vless",
          "settings": {
            "vnext": [
              {
                "address": "141.11.143.20",
                "port": 443,
                "users": [
                  {
                    "encryption": "none",
                    "id": "f93777cf-98bd-4722-ad2d-8a37302e91e8"
                  }
                ]
              }
            ]
          },
          "streamSettings": {
            "network": "grpc",
            "grpcSettings": {
              "serviceName": "sync.v1",
              "multiMode": true
            },
            "security": "tls",
            "tlsSettings": {
              "allowInsecure": false,
              "serverName": "3mtt3byxi6.j.cloudfront.hls.hlsnode.net",
              "fingerprint": "safari",
              "show": false,
              "alpn": ["h2"]
            },
            "sockopt": {
              "tcpKeepAliveInterval": 15,
              "tcpKeepAliveIdle": 30,
              "tcpNoDelay": true
            }
          },
          "tag": "srv-06-ea4af111"
        },
        {
          "protocol": "vless",
          "settings": {
            "vnext": [
              {
                "address": "151.245.192.203",
                "port": 443,
                "users": [
                  {
                    "encryption": "none",
                    "id": "f93777cf-98bd-4722-ad2d-8a37302e91e8"
                  }
                ]
              }
            ]
          },
          "streamSettings": {
            "network": "grpc",
            "grpcSettings": {
              "serviceName": "sync.v1",
              "multiMode": true
            },
            "security": "tls",
            "tlsSettings": {
              "allowInsecure": false,
              "serverName": "l71vtfjom2.j.cloudfront.hls.vodcdn-edge.net",
              "fingerprint": "safari",
              "show": false,
              "alpn": ["h2"]
            },
            "sockopt": {
              "tcpKeepAliveInterval": 15,
              "tcpKeepAliveIdle": 30,
              "tcpNoDelay": true
            }
          },
          "tag": "srv-07-ea4af111"
        },
        {
          "protocol": "vless",
          "settings": {
            "vnext": [
              {
                "address": "151.245.192.202",
                "port": 443,
                "users": [
                  {
                    "encryption": "none",
                    "id": "f93777cf-98bd-4722-ad2d-8a37302e91e8"
                  }
                ]
              }
            ]
          },
          "streamSettings": {
            "network": "grpc",
            "grpcSettings": {
              "serviceName": "sync.v1",
              "multiMode": true
            },
            "security": "tls",
            "tlsSettings": {
              "allowInsecure": false,
              "serverName": "l71vtfjom2.j.cloudfront.hls.vodcdn-edge.net",
              "fingerprint": "safari",
              "show": false,
              "alpn": ["h2"]
            },
            "sockopt": {
              "tcpKeepAliveInterval": 15,
              "tcpKeepAliveIdle": 30,
              "tcpNoDelay": true
            }
          },
          "tag": "srv-08-ea4af111"
        },
        {
          "protocol": "vless",
          "settings": {
            "vnext": [
              {
                "address": "151.245.192.232",
                "port": 443,
                "users": [
                  {
                    "encryption": "none",
                    "id": "f93777cf-98bd-4722-ad2d-8a37302e91e8"
                  }
                ]
              }
            ]
          },
          "streamSettings": {
            "network": "grpc",
            "grpcSettings": {
              "serviceName": "sync.v1",
              "multiMode": true
            },
            "security": "tls",
            "tlsSettings": {
              "allowInsecure": false,
              "serverName": "cugpbyxccy.j.cloudfront.hls.vodcdn-edge.net",
              "fingerprint": "safari",
              "show": false,
              "alpn": ["h2"]
            },
            "sockopt": {
              "tcpKeepAliveInterval": 15,
              "tcpKeepAliveIdle": 30,
              "tcpNoDelay": true
            }
          },
          "tag": "srv-09-ea4af111"
        },
        {
          "protocol": "vless",
          "settings": {
            "vnext": [
              {
                "address": "141.11.143.21",
                "port": 443,
                "users": [
                  {
                    "encryption": "none",
                    "id": "f93777cf-98bd-4722-ad2d-8a37302e91e8"
                  }
                ]
              }
            ]
          },
          "streamSettings": {
            "network": "grpc",
            "grpcSettings": {
              "serviceName": "sync.v1",
              "multiMode": true
            },
            "security": "tls",
            "tlsSettings": {
              "allowInsecure": false,
              "serverName": "3mtt3byxi6.j.cloudfront.hls.hlsnode.net",
              "fingerprint": "safari",
              "show": false,
              "alpn": ["h2"]
            },
            "sockopt": {
              "tcpKeepAliveInterval": 15,
              "tcpKeepAliveIdle": 30,
              "tcpNoDelay": true
            }
          },
          "tag": "srv-10-ea4af111"
        },
        {
          "protocol": "vless",
          "settings": {
            "vnext": [
              {
                "address": "151.245.192.195",
                "port": 443,
                "users": [
                  {
                    "encryption": "none",
                    "id": "f93777cf-98bd-4722-ad2d-8a37302e91e8"
                  }
                ]
              }
            ]
          },
          "streamSettings": {
            "network": "grpc",
            "grpcSettings": {
              "serviceName": "sync.v1",
              "multiMode": true
            },
            "security": "tls",
            "tlsSettings": {
              "allowInsecure": false,
              "serverName": "l71vtfjom2.j.cloudfront.hls.vodcdn-edge.net",
              "fingerprint": "safari",
              "show": false,
              "alpn": ["h2"]
            },
            "sockopt": {
              "tcpKeepAliveInterval": 15,
              "tcpKeepAliveIdle": 30,
              "tcpNoDelay": true
            }
          },
          "tag": "srv-11-ea4af111"
        },
        {
          "protocol": "vless",
          "settings": {
            "vnext": [
              {
                "address": "151.245.192.227",
                "port": 443,
                "users": [
                  {
                    "encryption": "none",
                    "id": "f93777cf-98bd-4722-ad2d-8a37302e91e8"
                  }
                ]
              }
            ]
          },
          "streamSettings": {
            "network": "grpc",
            "grpcSettings": {
              "serviceName": "sync.v1",
              "multiMode": true
            },
            "security": "tls",
            "tlsSettings": {
              "allowInsecure": false,
              "serverName": "cugpbyxccy.j.cloudfront.hls.vodcdn-edge.net",
              "fingerprint": "safari",
              "show": false,
              "alpn": ["h2"]
            },
            "sockopt": {
              "tcpKeepAliveInterval": 15,
              "tcpKeepAliveIdle": 30,
              "tcpNoDelay": true
            }
          },
          "tag": "srv-12-ea4af111"
        },
        {
          "protocol": "freedom",
          "tag": "direct"
        },
        {
          "protocol": "blackhole",
          "tag": "block"
        }
      ],
      "routing": {
        "domainMatcher": "hybrid",
        "domainStrategy": "IPIfNonMatch",
        "balancers": [
          {
            "fallbackTag": "srv-01-ea4af111",
            "selector": [
              "srv-01-ea4af111",
              "srv-02-ea4af111",
              "srv-03-ea4af111",
              "srv-04-ea4af111",
              "srv-05-ea4af111",
              "srv-06-ea4af111",
              "srv-07-ea4af111",
              "srv-08-ea4af111",
              "srv-09-ea4af111",
              "srv-10-ea4af111",
              "srv-11-ea4af111",
              "srv-12-ea4af111"
            ],
            "strategy": {
              "type": "leastPing"
            },
            "tag": "bal_auto"
          }
        ],
        "rules": [
          {
            "type": "field",
            "inboundTag": [
              "dns_in"
            ],
            "ip": [
              "77.88.8.8",
              "77.88.8.1"
            ],
            "outboundTag": "direct"
          },
          {
            "type": "field",
            "inboundTag": [
              "dns_in"
            ],
            "balancerTag": "bal_auto"
          },
          {
            "type": "field",
            "domain": [
              "full:probe.malina24.xyz"
            ],
            "balancerTag": "bal_auto"
          },
          {
            "type": "field",
            "protocol": [
              "bittorrent"
            ],
            "outboundTag": "direct"
          },
          {
            "type": "field",
            "domain": [
              "domain:ru",
              "domain:su",
              "domain:xn--p1ai",
              "domain:moscow",
              "domain:1tv.com",
              "domain:2gis.com",
              "domain:alfabank.com",
              "domain:alfabank.st",
              "domain:avito.com",
              "domain:avito.st",
              "domain:cian.site",
              "domain:clck.ly",
              "domain:ivi.tv",
              "domain:kinopoisk.com",
              "domain:kontur.host",
              "domain:megamarket.tech",
              "domain:moex.com",
              "domain:mycdn.me",
              "domain:naydex.net",
              "domain:ntv.tv",
              "domain:okko.tv",
              "domain:ozon.com",
              "domain:ozonusercontent.com",
              "domain:premier.one",
              "domain:rutube.me",
              "domain:sberbank.com",
              "domain:tank-online.com",
              "domain:tbank.com",
              "domain:tbank-online.com",
              "domain:tildaapi.com",
              "domain:tinkoff.com",
              "domain:userapi.com",
              "domain:vk.com",
              "domain:vk.me",
              "domain:vk-cdn.net",
              "domain:vk-portal.net",
              "domain:vkuser.net",
              "domain:vkuseraudio.net",
              "domain:vkuservideo.net",
              "domain:wbstatic.net",
              "domain:ya.cc",
              "domain:yandex.net",
              "domain:yandex.com",
              "domain:yandexcloud.net",
              "domain:yastatic.net",
              "full:go.yandex"
            ],
            "outboundTag": "direct"
          }
        ]
      }
    }
    """.trimIndent()
}
