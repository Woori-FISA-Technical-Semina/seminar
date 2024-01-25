package org.fisa.seminar.post;

import jakarta.persistence.*;
import lombok.*;
import org.fisa.seminar.member.Member;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter

@DynamicInsert
@DynamicUpdate

@Entity
@Table(name = "posting")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "post_id")
	private Long id;

	// 회원
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="account")
	private Member member;

	// 제목
	private String title;

	// 내용
	private String content;

	@Builder
	public Post(Member member, String title, String content) {
		this.member = member;
		this.title = title;
		this.content = content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
