package com.luckytree.shop.shop.adapter;

import com.luckytree.shop.shop.adapter.jpa.shop.*;
import com.luckytree.shop.shop.application.port.outgoing.ShopPort;
import com.luckytree.shop.shop.application.port.outgoing.ShopRemovePort;
import com.luckytree.shop.shop.domain.shop.SearchShopsCondition;
import com.luckytree.shop.shop.domain.shop.Shop;
import com.luckytree.shop.shop.domain.shop.ShopRemoveDetail;
import lombok.RequiredArgsConstructor;
import luckytree.poom.core.exceptions.NotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ShopAdapter implements ShopPort, ShopRemovePort {

    private final ShopRepository shopRepository;
    private final ShopRemoveRepository shopRemoveRepository;

    @Override
    public void save(Shop shop) {
        shopRepository.save(new ShopEntity(shop));
    }

    @Override
    public Shop findById(Long id) {
        return shopRepository.findById(id).orElseThrow(NotFoundException::new).toDomain();
    }

    @Override
    public List<Shop> findAll(SearchShopsCondition searchShopsCondition) {
        Specification<ShopEntity> shopEntityOf = ShopSpecification.searchByIdOrHashtagOrCategoryOrMaxLatOrMinLatOrMaxLngOrMinLng(searchShopsCondition);
        List<ShopEntity> shopEntities = shopRepository.findAll(shopEntityOf);
        return shopEntities.stream().map(ShopEntity::toDomain).toList();
    }

    @Override
    public void saveShopRemove(ShopRemoveDetail shopRemoveDetail) {
        shopRemoveRepository.save(new ShopRemoveEntity(shopRemoveDetail));
    }

    @Override
    public List<Shop> findShopsByIds(List<Long> shopIds) {
        return shopRepository.findAllByIdIn(shopIds).stream().map(ShopEntity::toDomain).toList();
    }
}
