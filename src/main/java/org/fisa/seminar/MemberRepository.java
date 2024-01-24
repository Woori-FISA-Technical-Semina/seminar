package org.fisa.seminar;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member save(Member member);
    Member findMemberByLoginId(String loginId);

    List<Member> findByLoginIdContainingOrNicknameContaining(String loginId, String nickname);

}

