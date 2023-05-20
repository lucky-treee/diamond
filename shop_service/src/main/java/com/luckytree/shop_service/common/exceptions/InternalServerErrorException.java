package com.luckytree.shop_service.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InternalServerErrorException extends RuntimeException {
    private String message;
}
