package com.example.auth.client.feign.member.data

import com.example.auth.domain.member.GenerateTokenRequest

class MemberFeignRequest(
    val id: Long,

    val email: String,
) {
    constructor(generateTokenRequest: GenerateTokenRequest): this(
        id = generateTokenRequest.id,
        email = generateTokenRequest.email,
    )
}
