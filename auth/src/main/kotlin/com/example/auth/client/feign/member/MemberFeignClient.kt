package com.example.auth.client.feign.member

import com.example.auth.client.feign.member.data.MemberFeignRequest
import com.example.auth.client.feign.member.data.MemberFeignResponse
import com.example.auth.config.InternalFeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(value = "member", url = "\${feign.member}", configuration = [InternalFeignConfig::class])
interface MemberFeignClient {
    @PostMapping("v1/users")
    fun findByIdAndEmail(memberFeignRequest: MemberFeignRequest): MemberFeignResponse
}