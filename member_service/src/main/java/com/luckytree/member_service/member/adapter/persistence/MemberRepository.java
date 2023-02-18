package com.luckytree.member_service.member.adapter.persistence;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByNicknameAndEmail(String nickname, String email);

    boolean existsByEmail(String email);
}