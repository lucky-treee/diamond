package com.luckytree.shop_service.shop.adapter.persistence;

import com.luckytree.shop_service.shop.adapter.data.Category;
import com.luckytree.shop_service.shop.domain.Hashtag;
import com.luckytree.shop_service.shop.domain.ShopStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {

    List<ShopEntity> findByCategoryAndStatus(Category category, ShopStatus shopStatus);

    List<ShopEntity> findByStatusAndLatLessThanEqualAndLatGreaterThanEqualAndLngLessThanEqualAndLngGreaterThanEqual(ShopStatus shopStatus, double maxLat, double minLat, double maxLng, double minLng);

    List<ShopEntity> findByHashtagAndStatus(Hashtag hashtag, ShopStatus shopStatus);

    Optional<ShopEntity> findByNameAndAddress(String name, String address);

    List<ShopEntity> findByIds(List<Long> ids);
}
