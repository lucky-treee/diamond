package com.luckytree.shop.shop.adapter.jpa.shop;

import com.luckytree.shop.shop.domain.shop.Shop;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;
import luckytree.poom.core.enums.ShopStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Table(name = "shop")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50)
    @Enumerated(value = EnumType.STRING)
    private ShopCategory category;

    @Column(length = 10, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ShopStatus status;

    @Column(length = 50, nullable = false)
    private String address;

    @Column(nullable = false)
    private double lat;

    @Column(nullable = false)
    private double lng;

    @Column(length = 50)
    @Enumerated(value = EnumType.STRING)
    private ShopHashtag hashtag;

    @Column(length = 150)
    private String photo;

    @Column(length = 50)
    private String contact;

    @Column(length = 50)
    private String sns;

    @Column(length = 50)
    private String homepage;

    @Column(name = "operating_start")
    private LocalTime operatingStart;

    @Column(name = "operating_end")
    private LocalTime operatingEnd;

    @Column(length = 20)
    private String holiday;

    @Column(name = "update_at")
    @LastModifiedDate
    private LocalDateTime updateAt;

    @Column(name = "create_at")
    @CreatedDate
    private LocalDateTime createAt;

    public ShopEntity(Shop shop) {
        this.name = shop.getName();
        this.category = shop.getCategory();
        this.status = shop.getStatus();
        this.address = shop.getAddress();
        this.lat = shop.getLat();
        this.lng = shop.getLng();
        this.photo = shop.getPhoto();
        this.contact = shop.getContact();
        this.sns = shop.getSns();
        this.homepage = shop.getHomepage();
        this.operatingStart = shop.getOperatingStart();
        this.operatingEnd = shop.getOperatingEnd();
        this.holiday = shop.getHoliday();
    }

    public Shop toDomain() {
        return new Shop(
                id,
                name,
                category,
                status,
                address,
                lat,
                lng,
                hashtag,
                photo,
                contact,
                sns,
                homepage,
                operatingStart,
                operatingEnd,
                holiday,
                updateAt,
                createAt
        );
    }
}
