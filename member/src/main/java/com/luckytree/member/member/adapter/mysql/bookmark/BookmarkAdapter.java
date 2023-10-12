package com.luckytree.member.member.adapter.mysql.bookmark;

import com.luckytree.member.member.application.port.outgoing.BookmarkPort;
import com.luckytree.member.member.domain.bookmark.Bookmark;
import lombok.RequiredArgsConstructor;
import luckytree.poom.core.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BookmarkAdapter implements BookmarkPort {

    private final BookmarkRepository bookmarkRepository;

    @Override
    @Transactional
    public void create(Bookmark bookmark) {
        bookmarkRepository.save(new BookmarkEntity(bookmark));
    }

    @Override
    public Bookmark findByMemberIdAndShopId(Long memberId, Long shopId) {
        return bookmarkRepository.findByMemberIdAndShopId(memberId, shopId).orElseThrow(NotFoundException::new).toDomain();
    }

    @Override
    public List<BookmarkEntity> findAllByMemberId(long memberId) {
        return bookmarkRepository.findAllByMemberId(memberId);
    }

    @Override
    @Transactional
    public void delete(Bookmark bookmark){
        bookmarkRepository.delete(new BookmarkEntity(bookmark));
    }
}
