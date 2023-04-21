package com.luckytree.shop_service.shop.adapter.jpa;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "review")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private ShopEntity shopEntity;

    @Column(name = "member_id", length = 20, nullable = false)
    private Long memberId;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "update_at")
    @LastModifiedDate
    private LocalDateTime updateAt;

    @Column(name = "create_at")
    @CreatedDate
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "reviewPhotoEntity")
    private List<ReviewPhotoEntity> reviewPhotoEntityList;
}
