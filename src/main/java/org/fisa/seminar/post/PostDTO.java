package org.fisa.seminar.post;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Builder
public class PostDTO {
    private String loginId;
    private String title;
    private String content;

    // 검색 조회에 필요한 작성자
    private String author;
}
