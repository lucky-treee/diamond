package com.luckytree.shop_service.shop.adapter.jpa;

import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.common.enums.Hashtag;
import com.luckytree.shop_service.common.enums.ShopStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {

    List<ShopEntity> findByCategoryAndStatus(Category category, ShopStatus shopStatus);

    List<ShopEntity> findByStatusAndLatLessThanEqualAndLatGreaterThanEqualAndLngLessThanEqualAndLngGreaterThanEqual(ShopStatus shopStatus, double maxLat, double minLat, double maxLng, double minLng);

    List<ShopEntity> findByHashtagAndStatus(Hashtag hashtag, ShopStatus shopStatus);

    Optional<ShopEntity> findByNameAndAddress(String name, String address);

    List<ShopEntity> findAllByIdIn(List<Long> id);
}