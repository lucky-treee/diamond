package com.luckytree.member_service.email.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class Mail {

    private String address;
    private String title;
    private String message;

    public Mail(String address, String title, String message) {
        this.address = address;
        this.title = title;
        this.message = message;
    }
}


