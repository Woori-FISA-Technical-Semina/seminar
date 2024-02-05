package org.fisa.seminar.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fisa.seminar.member.Member;
import org.fisa.seminar.member.MemberDTO;
import org.fisa.seminar.member.MemberRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /**
     * 글 작성
     * @param postDTO
     * @return 저장된 글
     */
//    @Cacheable(value = "post", key = "#postDTO", cacheManager = "contentCacheManager")
    public void writePost(PostDTO postDTO) throws RuntimeException {
//        Member member = memberRepository.findMemberByLoginId(postDTO.getLoginId());
        Member member = memberRepository.findById(postDTO.getMemberId())
                .orElseThrow(() -> new RuntimeException("해당 아이디를 가진 회원이 존재하지 않습니다."));

        log.info("member {}", member.getId());

        Post post = Post.builder()
                .member(member)
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .build();

        postRepository.save(post);
    }

    /**
     * 글 조회
     * @param postId
     * @return 조회된 글
     */
    public PostDTO getPost(Long postId) throws RuntimeException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("해당 ID를 가진 글이 존재하지 않습니다."));

        return PostDTO.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    /**
     * 글 수정
     * @param postId
     * @param postDTO
     */
    public void updatePost(Long postId, PostDTO postDTO) throws RuntimeException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("해당 ID를 가진 글이 존재하지 않습니다."));

        post.setContent(postDTO.getContent());
        postRepository.save(post);
    }

    /**
     * 글 삭제
     * @param postId
     */
    public void deletePost(Long postId) throws RuntimeException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("해당 ID를 가진 글이 존재하지 않습니다."));

        postRepository.delete(post);
    }

    /**
     * 검색 키워드에 해당하는 회원이 작성한 게시글 찾기
     * @param keyword 검색 키워드
     * @return 검색 결과
     */
    @Cacheable(value = "search", key = "#keyword", cacheManager = "contentCacheManager")
    public List<PostDTO> search(String keyword) throws RuntimeException {
        try {
            // 회원 이름이나 닉네임에 검색 키워드가 포함된 회원을 찾습니다.
            List<Member> matchingMembers = memberRepository.findByLoginIdContainingOrNicknameContaining(keyword, keyword);
            log.info("matchingMembers {}", matchingMembers);

            // 검색된 회원들의 ID로 해당 회원이 작성한 게시글을 찾습니다.
            List<Post> matchingPosts = postRepository.findByMemberIn(matchingMembers);
            log.info("matchingPosts {}", matchingPosts);

            // 검색된 게시글 정보를 DTO로 변환합니다.
            return matchingPosts.stream()
                    .map(post -> PostDTO.builder()
                            .loginId(post.getMember().getLoginId())
                            .title(post.getTitle())
                            .content(post.getContent())
                            .author(post.getMember().getNickname())  // 작성자 정보
                            .build())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("검색 중 에러가 발생했습니다.", e);
        }
    }
}
