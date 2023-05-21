package luckytree.poom.core.jwt

data class JwtAuthentication(
    val token: String,

    val userId: Long,
)
