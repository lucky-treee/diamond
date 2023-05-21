package com.luckytree.shop.shop.adapter.data.shop;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ShopSearchDto {

   private double maxLat;
   private double minLat;
   private double maxLng;
   private double minLng;
}
