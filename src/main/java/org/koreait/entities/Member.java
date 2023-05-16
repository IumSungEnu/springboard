package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(indexes = {
        @Index(name = "idx_member_userNm", columnList = "userNm"),
        @Index(name = "idx_member_email", columnList = "email")
})
public class Member extends BaseEntity{ //회원가입 엔티티
    
    @Id @GeneratedValue
    private Long userNo;       //회원번호 - 조인, 외래키로 자주 쓰일예정
    
    //nullable = false - 필수 항목
    @Column(length = 40, nullable = false, unique = true)
    private String userId;     //회원아이디
    
    @Column(length = 65, nullable = false)
    private String userPw;     //비밀번호
    
    @Column(length = 40, nullable = false)
    private String userNm;     //회원명
    
    @Column(length = 100, nullable = false)
    private String email;      //이메일 -> 이메일을 보내 비밀번호 초기화 - 토큰을 보내서(제한시간 o)
    
    @Column(length = 11)
    private String mobile;     //휴대전화번호
    
    @Lob
    private String termsAgree; //약관 동의 내역 -> json 형태로 사용
}
