package org.fisa.seminar.post;

import org.fisa.seminar.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    Post save(Post post);

    @Query("select p from Post p")
    List<Post> findPostByPostId(Long postId);

    List<Post> findByMemberIn(List<Member> members);


}