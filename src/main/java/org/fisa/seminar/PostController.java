package org.fisa.seminar;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/controller")
public class PostController {

    private final PostService postService;

    /**
     * 회원 생성
     * @param memberDTO
     */
    @PostMapping(value = "/member")
    public ResponseEntity<?> createMember(@RequestBody MemberDTO memberDTO) {
        try {
            postService.createMember(memberDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError() // Error 500
                    .body(e.getMessage());
        }
    }

    /**
     * 글 작성
     * @param postDTO
     */
    @PostMapping(value = "/post")
    public ResponseEntity<?> writePost(@RequestBody PostDTO postDTO) {
        try {
            postService.writePost(postDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError() // Error 500
                    .body(e.getMessage());
        }
    }

    /**
     * 글 조회
     * @param postId
     */
    @GetMapping(value = "/post")
    public ResponseEntity<?> getPost(@RequestParam Long postId) {
        try {
            PostDTO postDTO = postService.getPost(postId);
            return ResponseEntity.ok(postDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

    /**
     * 글 수정
     * @param postId
     * @param postDTO
     */
    @PutMapping(value = "/post")
    public ResponseEntity<?> updatePost(@RequestParam Long postId, @RequestBody PostDTO postDTO) {
        try {
            postService.updatePost(postId, postDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

    /**
     * 글 삭제
     * @param postId
     */
    @DeleteMapping(value = "/post")
    public ResponseEntity<?> deletePost(@RequestParam Long postId) {
        try {
            postService.deletePost(postId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

    /**
     * 회원 이름이나 닉네임에 검색 키워드가 포함된 회원을 찾은 후에 검색된 회원들의 ID로 해당 회원이 작성한 게시글 찾기
     * @param keyword 검색 키워드
     * @return 검색 결과
     */
    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> search(@RequestParam String keyword) {
        try {
            List<PostDTO> searchResults = postService.search(keyword);
            return ResponseEntity.ok(searchResults);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(Collections.emptyList());
        }
    }
}
