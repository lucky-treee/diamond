package com.luckytree.member_service.member.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {

    Optional<List<BookmarkIdInterface>> findAllByMember_id(long memberId);
}
