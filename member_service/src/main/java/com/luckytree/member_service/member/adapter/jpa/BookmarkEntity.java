package com.luckytree.member_service.member.adapter.jpa;

import com.luckytree.member_service.common.enums.Category;
import com.luckytree.member_service.member.adapter.data.CreateBookmarkRequest;
import com.luckytree.member_service.member.domain.Bookmark;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "bookmark")
@EntityListeners(AuditingEntityListener.class)
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
public class BookmarkEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "member_id", length = 20, unique = true, nullable = false)
    private Long memberId;

    @Column(name= "shop_id", length = 20, nullable = false)
    private Long shopId;

    @Column(length = 50)
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Column(name = "create_at")
    @CreatedDate
    private LocalDateTime createAt;

    public BookmarkEntity(Bookmark bookmark) {
        this.memberId = bookmark.getMemberId();
        this.shopId = bookmark.getShopId();
        this.category = bookmark.getCategory();
    }

    public BookmarkEntity(CreateBookmarkRequest createBookmarkRequest) {
        this.memberId = createBookmarkRequest.getMemberId();
        this.shopId = createBookmarkRequest.getShopId();
        this.category = createBookmarkRequest.getCategory();
    }
}