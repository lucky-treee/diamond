package com.luckytree.member.member.application.service;

import com.luckytree.member.member.adapter.data.member.GetMemberResponse;
import com.luckytree.member.member.adapter.data.member.MemberResponse;
import com.luckytree.member.member.application.port.incoming.MemberUseCase;
import com.luckytree.member.member.application.port.outgoing.MemberPort;
import com.luckytree.member.member.domain.member.Member;
import com.luckytree.member.member.domain.member.MemberDetail;
import lombok.RequiredArgsConstructor;
import luckytree.poom.core.enums.MemberStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static luckytree.poom.core.enums.MemberStatus.LEAVE;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {

    private final MemberPort memberPort;

    @Transactional(readOnly = true)
    @Override
    public MemberResponse getMember(Long id) {
        Member member = memberPort.findById(id);

        return new MemberResponse(member);
    }

    @Override
    public GetMemberResponse getMember(Member member) {
        return new GetMemberResponse(memberPort.findByIdAndEmail(member));
    }

    @Transactional
    @Override
    public void update(MemberDetail memberDetail) {
        Member member = memberPort.findById(memberDetail.getId());
        member.update(memberDetail);
        memberPort.save(member);
    }

    @Transactional
    @Override
    public void leave(Long memberId) {
        Member member = memberPort.findById(memberId);
        member.update(MemberStatus.LEAVE, member.getId());
        memberPort.save(member);
    }

    @Transactional
    @Override
    public MemberResponse create(Member member) {
        return new MemberResponse(memberPort.save(member));
    }
}
