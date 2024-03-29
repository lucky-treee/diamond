package com.luckytree.member.member.adapter.mysql.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByIdAndEmail(Long id, String email);
}