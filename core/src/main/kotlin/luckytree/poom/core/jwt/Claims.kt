package luckytree.poom.core.jwt

import com.auth0.jwt.interfaces.DecodedJWT
import java.util.Date

class Claims(
    val memberId: Long,

    val role: String,

    val issuedAt: Date? = null,

    val expiresAt: Date? = null,
) {
    constructor(decodedJWT: DecodedJWT) : this(
        memberId = decodedJWT.getClaim(MEMBER_ID).asLong(),
        role = Role.from(decodedJWT.getClaim(ROLE).asString()).role,
        issuedAt = decodedJWT.issuedAt,
        expiresAt = decodedJWT.expiresAt,
    )

    companion object {
        private const val MEMBER_ID = "memberId"

        private const val ROLE = "role"
    }
}
