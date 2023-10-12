package com.example.auth.domain.member

import luckytree.poom.core.jwt.Role

class Member(
    val id: Long,

    val role: Role,
) {
    fun role() = role.role
}
