package com.luckytree.member_service.member.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByNicknameAndEmail(String nickname, String email);
    Optional<MemberEntity> findByEmail(String email);
    Optional<MemberEntity> findByNickname(String nickname);
}