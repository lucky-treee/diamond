package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.member.adapter.data.MyBookmarksDto;
import com.luckytree.member_service.member.adapter.persistence.BookmarkEntity;
import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import com.luckytree.member_service.member.application.port.incoming.MemberUseCase;
import com.luckytree.member_service.member.application.port.outgoing.MemberPort;
import com.luckytree.member_service.member.application.port.outgoing.ShopFeignClientPort;
import com.luckytree.member_service.member.domain.Member;
import com.luckytree.member_service.member.domain.MemberProfile;
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
    public MemberProfile getMemberProfile(long memberId) {
        Member member = memberPort.findMemberById(memberId);
        return new MemberProfile(member);
    }

    @Transactional
    @Override
    public void updateMember(String email, String nickname, String photo) {
        Member member = getMember(email);
        member.updateNicknameAndPhoto(nickname, photo);
        memberPort.updateMember(member);
    }

    @Transactional
    @Override
    public void leaveMember(long memberId) {
        MemberEntity memberEntity = memberPort.findById(memberId);
        memberEntity.isAlreadyDeleted();
        memberPort.deleteById(memberEntity);
    }

    @Override
    public MyBookmarksDto findMyBookmarks(long memberId) {
        List<Long> shopIds = findShopIds(memberId);
        return findMyBookmarksFeign(shopIds);
    }

    @Transactional
    @Override
    public void deleteBookMark(long memberId, String shopId) {

    }

    private Member getMember(String email) {
        return memberPort.findByEmail(email);
    }

    private List<Long> findShopIds(long memberId) {
        List<BookmarkEntity> bookmarkEntities = memberPort.findBookmarksByMemberId(memberId);
        return bookmarkEntities.stream().map(BookmarkEntity::getShopId).toList();
    }

    private MyBookmarksDto findMyBookmarksFeign(List<Long> shopIds) {
        return shopFeignClientPort.findBookmarksByIds(shopIds);
    }
}
