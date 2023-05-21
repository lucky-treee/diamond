package com.luckytree.shop.shop.adapter.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luckytree.shop.common.enums.Category;
import com.luckytree.shop.common.jwt.TokenProvider;
import com.luckytree.shop.shop.adapter.data.CreateShopDto;
import com.luckytree.shop.shop.application.service.ShopService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(ShopController.class)
class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private TokenProvider tokenProvider;

    @MockBean
    private ShopService shopService;

    private static final String BASE_URL = "/v1/shops";


    @DisplayName("[API][POST] 가게 등록")
    @Test
    void createShopTest() throws Exception {
        // given
        doNothing().when(shopService).createShop(any());

        // when
        String body = objectMapper.writeValueAsString(makeCreateShopDto());

        MvcResult mvcResult = mockMvc.perform(
                post(BASE_URL + "/shop", 10)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(body)
        ).andExpect(status().isOk()).andReturn();

        // then
        int code = mvcResult.getResponse().getStatus();
        assertEquals(code, 200);
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
