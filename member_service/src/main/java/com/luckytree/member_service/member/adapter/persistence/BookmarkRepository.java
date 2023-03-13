package com.luckytree.member_service.member.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {

    @Query(value = "select shop_id from bookmark WHERE member_id = :memberId", nativeQuery = true)
    List<Long> findAllByMemberId(@Param("memberId") long memberId);
}
