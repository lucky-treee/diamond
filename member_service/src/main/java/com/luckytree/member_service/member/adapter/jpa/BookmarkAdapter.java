package com.luckytree.member_service.member.adapter.jpa;

import com.luckytree.member_service.member.adapter.data.CreateBookmarkRequest;
import com.luckytree.member_service.member.application.port.outgoing.BookmarkPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BookmarkAdapter implements BookmarkPort {

    private final BookmarkRepository bookmarkRepository;

    @Override
    public void create(CreateBookmarkRequest createBookmarkRequest) {
        bookmarkRepository.save(new BookmarkEntity(createBookmarkRequest));
    }

    @Override
    public List<BookmarkEntity> findAllByMemberId(long memberId) {
        return bookmarkRepository.findAllByMemberId(memberId);
    }

    @Override
    public void deleteByMemberIdAndShopId(long memberId, long shopId){
        bookmarkRepository.deleteByMemberIdAndShopId(memberId, shopId);
    }
}
