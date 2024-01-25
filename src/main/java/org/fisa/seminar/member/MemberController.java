package org.fisa.seminar.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fisa.seminar.post.PostDTO;
import org.fisa.seminar.post.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 생성
     * @param memberDTO
     */
    @PostMapping(value = "/member")
    public ResponseEntity<?> createMember(@RequestBody MemberDTO memberDTO) {
        try {
            memberService.createMember(memberDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError() // Error 500
                    .body(e.getMessage());
        }
    }
}
