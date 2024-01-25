package org.fisa.seminar.member;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Builder
public class MemberDTO {
    private String loginId;
    private String name;
    private String nickname;
}