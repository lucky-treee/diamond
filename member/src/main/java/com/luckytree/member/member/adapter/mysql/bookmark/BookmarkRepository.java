package com.luckytree.member.member.adapter.mysql.bookmark;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {

    List<BookmarkEntity> findAllByMemberId(long memberId);

    Optional<BookmarkEntity> findByMemberIdAndShopId(Long memberId, Long shopId);
}
