package com.luckytree.member.member.application.port.outgoing;

import com.luckytree.member.member.domain.member.Member;
import com.luckytree.member.member.domain.member.MemberDetail;

public interface MemberPort {

    Member save(Member member);

    Member findById(Long id);

    Member findByIdAndEmail(Member member);
}
