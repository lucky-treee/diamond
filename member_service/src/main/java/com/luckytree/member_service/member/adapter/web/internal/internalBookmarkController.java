package com.luckytree.member_service.member.adapter.web.internal;

import com.luckytree.member_service.member.adapter.data.CreateBookmarkDto;
import com.luckytree.member_service.member.adapter.data.MemberFeignRequestDto;
import com.luckytree.member_service.member.application.port.incoming.MemberUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Internal API", description = "호출 X")
@RestController
@RequestMapping("/v1/bookmarks")
@RequiredArgsConstructor
public class internalBookmarkController {

    private final MemberUseCase memberUseCase;

    @PostMapping("/member")
    public void createBookmark(@RequestBody MemberFeignRequestDto memberFeignRequestDto) {
        memberUseCase.createBookmark(new CreateBookmarkDto(memberFeignRequestDto));
    }
}