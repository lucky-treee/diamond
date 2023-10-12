package com.example.auth.domain.member

import org.jetbrains.annotations.NotNull

data class GenerateTokenRequest(
    @field:NotNull
    val id: Long,

    @field:NotNull
    val email: String,
)
