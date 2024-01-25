package org.fisa.seminar.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fisa.seminar.post.Post;
import org.fisa.seminar.post.PostDTO;
import org.fisa.seminar.post.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 멤버 생성
     * @param memberDTO
     * @return 저장된 멤버
     */
    public Member createMember(MemberDTO memberDTO) throws RuntimeException {

        Member member = Member.builder()
                .loginId(memberDTO.getLoginId())
                .name(memberDTO.getName())
                .nickname(memberDTO.getNickname())
                .build();

        return memberRepository.save(member);
    }
}
