package com.example.auth.client.feign.member

import com.example.auth.client.feign.member.data.MemberFeignResponse
import com.example.auth.config.InternalFeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "member", configuration = [InternalFeignConfig::class])
interface MemberFeignClient {
    @GetMapping("member/v1/members")
    fun findByIdAndEmail(@RequestParam id: Long, @RequestParam email: String): MemberFeignResponse
}
