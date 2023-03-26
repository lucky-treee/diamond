package com.luckytree.member_service.member.adapter.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {

    List<BookmarkEntity> findAllByMemberId(long memberId);

    void deleteByMemberIdAndShopId(long memberId, long shopId);
}
