package com.example.auth.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm.HMAC512
import luckytree.poom.core.config.JwtConfiguration
import luckytree.poom.core.jwt.Claims
import luckytree.poom.core.jwt.Token
import org.springframework.stereotype.Component
import java.util.*

@Component
class Jwt(private val jwtConfiguration: JwtConfiguration) {
    private val algorithm = HMAC512(jwtConfiguration.secret)

    private val jwtVerifier = JWT.require(algorithm).withIssuer(jwtConfiguration.issuer).build()

    private val accessTokenExpireTime = jwtConfiguration.accessTokenExpireMinute * 60 * 1000

    private val refreshTokenExpireTime = jwtConfiguration.refreshTokenExpireMinute * 60 * 1000

    fun refreshToken(token: String): Token {
        return with(verify(token)) { generateToken(this) }
    }

    private fun verify(token: String) = Claims(jwtVerifier.verify(token))

    fun generateToken(claims: Claims): Token {
        return with(Date()) {
            val accessToken = createJwt(claims, this, accessTokenExpireTime)
            val refreshToken = createJwt(claims, this, refreshTokenExpireTime)

            Token(accessToken, refreshToken)
        }
    }

    private fun createJwt(claims: Claims, date: Date, tokenExpireTime: Int): String {
        return JWT.create().apply {
            withClaim(MEMBER_ID, claims.memberId)
            withClaim(ROLE, claims.role)
            withIssuer(jwtConfiguration.issuer)
            withIssuedAt(date)
            withExpiresAt(Date(date.time + tokenExpireTime))
        }.sign(algorithm)
    }

    companion object {
        private const val MEMBER_ID = "memberId"

        private const val ROLE = "role"
    }
}
