package com.luckytree.shop.shop.adapter.jpa.shop;

import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;
import luckytree.poom.core.enums.ShopStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {

    List<ShopEntity> findByCategoryAndStatus(ShopCategory category, ShopStatus shopStatus);

    List<ShopEntity> findByStatusAndLatLessThanEqualAndLatGreaterThanEqualAndLngLessThanEqualAndLngGreaterThanEqual(ShopStatus shopStatus, double maxLat, double minLat, double maxLng, double minLng);

    List<ShopEntity> findByHashtagAndStatus(ShopHashtag hashtag, ShopStatus shopStatus);

    Optional<ShopEntity> findByNameAndAddress(String name, String address);

    Optional<ShopEntity> findById(long shopId);

    List<ShopEntity> findAllByIdIn(List<Long> id);
}