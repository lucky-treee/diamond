package com.luckytree.member_service.member.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {

    List<BookmarkEntity> findAllByMemberId(long memberId);

    void deleteBookmarkByMemberIdAndShopId(long memberId, long shopId);
}
