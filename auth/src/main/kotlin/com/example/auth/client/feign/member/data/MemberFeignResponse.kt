package com.example.auth.client.feign.member.data

import com.example.auth.domain.member.Member
import luckytree.poom.core.jwt.Role

class MemberFeignResponse(
    val id: Long,

    val role: Role,
) {
    fun toDomain(): Member {
        return Member(
            id = id,
            role = role,
        )
    }
}
