package org.fisa.seminar.member;

import jakarta.persistence.*;
import lombok.*;
import org.fisa.seminar.post.Post;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter

@DynamicInsert
@DynamicUpdate

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    // 로그인 아이디
    @Column(nullable = false, unique = true)
    private String loginId;

    // 이름
    private String name;

    // 별명
    private String nickname;

    // 게시글
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="post")
//    private Post post;
    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String loginId, String name, String nickname) {
        this.loginId = loginId;
        this.name = name;
        this.nickname = nickname;
    }
}