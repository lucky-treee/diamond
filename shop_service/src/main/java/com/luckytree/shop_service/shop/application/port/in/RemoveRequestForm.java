package com.luckytree.shop_service.shop.application.port.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RemoveRequestForm {

    @NotBlank
    @Size(max = 50)
    private  String name;

    @NotBlank
    @Size(max = 50)
    private String address;

    @NotBlank
    private String comment;
}
