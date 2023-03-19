package com.luckytree.member_service.member.adapter.persistence;

import com.luckytree.member_service.member.application.port.outgoing.BookmarkPort;
import com.luckytree.member_service.member.domain.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BookmarkAdapter implements BookmarkPort {

    private final BookmarkRepository bookmarkRepository;

    @Override
    public void createBookmark(Bookmark bookmark) {
       bookmarkRepository.save(new BookmarkEntity(bookmark));
    }
}