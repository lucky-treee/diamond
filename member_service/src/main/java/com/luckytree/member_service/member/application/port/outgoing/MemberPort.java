package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.adapter.persistence.BookmarkEntity;
import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import com.luckytree.member_service.member.domain.Bookmark;
import com.luckytree.member_service.member.domain.Member;

import java.util.List;

public interface MemberPort {

    Member findMemberById(long memberId);
    Member findByEmail(String email);
    void updateMember(Member member);
    void deleteById(MemberEntity memberEntity);
    void createBookmark(Bookmark bookmark);
    MemberEntity findById(long memberId);
    List<BookmarkEntity> findBookmarksByMemberId(long memberId);
}
