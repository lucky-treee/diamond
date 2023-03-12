package com.luckytree.shop_service.shop.adapter.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@NoArgsConstructor
@Getter
@Setter
public class ShopLatLngRequest {

   private double maxLat;
   private double minLat;
   private double maxLng;
   private double minLng;
}
