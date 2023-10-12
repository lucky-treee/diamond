package com.luckytree.member.member.adapter.mysql.bookmark;

import com.luckytree.member.member.domain.bookmark.Bookmark;
import jakarta.persistence.*;
import lombok.*;
import luckytree.poom.core.enums.ShopCategory;
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
    private ShopCategory category;

    @Column(name = "create_at")
    @CreatedDate
    private LocalDateTime createAt;

    public BookmarkEntity(Bookmark bookmark) {
        this.id = bookmark.getId();
        this.memberId = bookmark.getMemberId();
        this.shopId = bookmark.getShopId();
        this.category = bookmark.getCategory();
        this.createAt = bookmark.getCreateAt();
    }

    public Bookmark toDomain() {
        return new Bookmark(id, memberId, shopId, category, createAt);
    }
}
