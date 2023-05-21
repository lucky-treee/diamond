package com.example.auth.client.feign.member

import com.example.auth.client.feign.member.data.MemberFeignRequest
import org.springframework.stereotype.Repository

@Repository
class MemberFeignRepository(
    private val memberFeignClient: MemberFeignClient,
) {
    fun findByIdAndEmail(memberFeignRequest: MemberFeignRequest) =
        memberFeignClient.findByIdAndEmail(memberFeignRequest)
}
