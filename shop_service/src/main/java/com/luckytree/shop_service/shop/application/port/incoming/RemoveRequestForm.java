package com.luckytree.shop_service.shop.application.port.incoming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "샵 삭제 요청")
@NoArgsConstructor
public class RemoveRequestForm {

    @NotBlank
    @Size(max = 50)
    @Schema(description = "샵 이름")
    private  String name;

    @NotBlank
    @Size(max = 50)
    @Schema(description = "샵 주소")
    private String address;

    @NotBlank
    @Schema(description = "샵 삭제사유")
    private String comment;
}
