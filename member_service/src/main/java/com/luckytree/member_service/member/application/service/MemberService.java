package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.common.utils.TokenUtil;
import com.luckytree.member_service.member.adapter.data.MemberResponse;
import com.luckytree.member_service.member.adapter.data.ReviewsResponse;
import com.luckytree.member_service.member.adapter.data.UpdateMemberRequest;
import com.luckytree.member_service.member.adapter.data.ReviewResponse;
import com.luckytree.member_service.member.adapter.jpa.MemberEntity;
import com.luckytree.member_service.member.application.port.incoming.MemberUseCase;
import com.luckytree.member_service.member.application.port.outgoing.MemberPort;
import com.luckytree.member_service.member.application.port.outgoing.ShopFeignClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {

    private final MemberPort memberPort;
    private final ShopFeignClientPort shopFeignClientPort;

    @Transactional(readOnly = true)
    @Override
    public MemberResponse getMember(String authorization) {
        Long memberId = TokenUtil.parseMemberId(authorization);

        MemberEntity memberEntity = memberPort.findById(memberId);

        return new MemberResponse(memberEntity);
    }

    @Transactional
    @Override
    public void update(String authorization, UpdateMemberRequest updateMemberRequest) {
        Long memberId = TokenUtil.parseMemberId(authorization);

        MemberEntity memberEntity = memberPort.findById(memberId);
        memberEntity.update(updateMemberRequest.getNickname(), updateMemberRequest.getPhoto());
    }

    @Transactional
    @Override
    public void leave(String authorization) {
        Long memberId = TokenUtil.parseMemberId(authorization);

        MemberEntity memberEntity = memberPort.findById(memberId);
        memberEntity.isAlreadyDeleted();
        memberPort.deleteById(memberEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public ReviewsResponse getMyReviews(String authorization) {
        Long memberId = TokenUtil.parseMemberId(authorization);
        return shopFeignClientPort.findShopMyReviewsById(memberId);
    }
}