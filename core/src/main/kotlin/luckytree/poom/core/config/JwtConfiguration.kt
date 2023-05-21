package luckytree.poom.core.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jwt")
class JwtConfiguration(
    val header: String,

    val issuer: String,

    val secret: String,

    val accessTokenExpireMinute: Int,

    val refreshTokenExpireMinute: Int,
)
