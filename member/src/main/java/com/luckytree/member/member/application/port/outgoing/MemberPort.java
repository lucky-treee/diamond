package com.luckytree.member.member.application.port.outgoing;

import com.luckytree.member.member.adapter.jpa.MemberEntity;
import com.luckytree.member.member.domain.member.Member;

public interface MemberPort {

    Member save(Member member);

    Member findById(long id);

    Member findByIdAndEmail(Member member);
}
