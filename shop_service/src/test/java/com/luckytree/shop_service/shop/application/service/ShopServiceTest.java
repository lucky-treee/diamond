package com.luckytree.shop_service.shop.application.service;

import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.shop.adapter.data.CreateShopDto;
import com.luckytree.shop_service.shop.application.port.outgoing.ShopPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShopServiceTest {

    @Mock
    private ShopPort shopPort;

    @InjectMocks
    private ShopService shopService;

    @DisplayName("가게 등록")
    @Test
    void createShop() {
        // given
        doNothing().when(shopPort).createShop(any());

        // when
        shopService.createShop(makeCreateShopDto());

        // then
        verify(shopPort, times(1)).createShop(any());
    }

    private CreateShopDto makeCreateShopDto() {
        CreateShopDto createShopDto = new CreateShopDto();
        createShopDto.setShopName("행복상점");
        createShopDto.setCategory(Category.NECESSITIES);
        createShopDto.setAddress("서울특별시 관악구 봉천동 1714-10");
        createShopDto.setPhoto("https://awsawsawstest.com/tomatophoto.jpg");
        createShopDto.setContact("010-1234-5678");
        createShopDto.setHomepage("https://www.happystore.com");
        createShopDto.setFlagshipProduct("요술지팡이");
        createShopDto.setSns("testInstagram");
        createShopDto.setLat(37.56789);
        createShopDto.setLng(128.56789);
        createShopDto.setOperatingStart(LocalTime.of(10, 30));
        createShopDto.setOperatingEnd(LocalTime.MIDNIGHT);
        createShopDto.setHoliday("매주 월요일");
        return createShopDto;
    }
}