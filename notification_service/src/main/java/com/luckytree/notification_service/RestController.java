package com.luckytree.notification_service;

import com.luckytree.notification_service.config.FeignClients;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 통신 테스트를 위한 임시 컨트롤러 - 삭제 예정
 */

@RequiredArgsConstructor
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/notification")
public class RestController {

    private final FeignClients feignClients;

    @GetMapping("/noti-test")
    String startCall() {
        String name = "junyeong";
        String result = feignClients.call(name);
        return result;
    }
}
