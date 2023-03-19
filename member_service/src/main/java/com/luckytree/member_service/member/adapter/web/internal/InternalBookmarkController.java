package com.luckytree.member_service.member.adapter.web.internal;

import com.luckytree.member_service.member.application.port.incoming.MemberUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Internal API", description = "호출 X")
@RestController
@RequestMapping("/v1/bookmarks")
@RequiredArgsConstructor
public class InternalBookmarkController {

    private final MemberUseCase memberUseCase;

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Object> deleteBookmark(@PathVariable long memberId, @RequestParam(name = "shopId") long shopId) {
        memberUseCase.deleteBookMark(memberId, shopId);
        return ResponseEntity.ok().build();
    }
}
