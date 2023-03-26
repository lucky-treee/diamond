package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.adapter.persistence.BookmarkEntity;
import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import com.luckytree.member_service.member.domain.Bookmark;
import com.luckytree.member_service.member.domain.Member;

import java.util.List;

public interface MemberPort {

    MemberEntity findById(long memberId);
    void deleteById(MemberEntity memberEntity);
}
