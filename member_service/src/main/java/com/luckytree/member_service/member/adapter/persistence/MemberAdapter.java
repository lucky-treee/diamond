package com.luckytree.member_service.member.adapter.persistence;

import com.luckytree.member_service.common.advice.NotFoundException;
import com.luckytree.member_service.member.adapter.data.ShopDetailDto;
import com.luckytree.member_service.member.adapter.feign.BookmarkFeignClient;
import com.luckytree.member_service.member.application.port.outgoing.MemberPort;
import com.luckytree.member_service.member.domain.Member;
import com.luckytree.member_service.member.domain.MemberProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberAdapter implements MemberPort {

    private final MemberRepository memberRepository;
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkFeignClient bookmarkFeignClient;

    @Override
    public Member findMemberById(long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));
        return memberEntity.toDomain();
    }

    @Override
    public Member findByEmail(String email) {
        MemberEntity memberEntity = memberRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("해당 이메일이 존재하지 않습니다."));
        return new Member(memberEntity);
    }

    @Override
    @Transactional
    public void updateMember(Member member){
        MemberEntity memberEntity = memberRepository.findByEmail(member.getEmail()).orElseThrow(() -> new NotFoundException("해당 이메일이 존재하지 않습니다."));
        memberEntity.updateNicknameAndPhoto(member.getNickname(), member.getPhoto());
    }

    @Override
    public List<Long> getBookmarkIds(long memberId){
        List<Long> bookmarkIdList = bookmarkRepository.findAllByMemberId(memberId);
        return bookmarkIdList;
    }

    @Override
    public List<ShopDetailDto> getBookmark(List<Long> bookmarkIds){
        return bookmarkFeignClient.findShopByIds(bookmarkIds);
    }

}
