package com.luckytree.shop.shop.adapter.jpa.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ShopRepository extends JpaRepository<ShopEntity, Long>, JpaSpecificationExecutor<ShopEntity> {

    List<ShopEntity> findAllByIdIn(List<Long> id);
}
