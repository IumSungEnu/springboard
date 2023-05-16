package org.koreait.controllers.members;

import lombok.RequiredArgsConstructor;
import org.koreait.repositories.MemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {  //커맨드 객체(join)
        return JoinForm.class.isAssignableFrom(clazz);  //joinForm에 있는 데이터만 조회
    }

    @Override
    public void validate(Object target, Errors errors) {
        /**
         * 1. 아이디 중복 여부
         * 2. 비밀번호 복잡성 체크(알파벳 - 대,소문자 , 숫자, 특수문자)
         * 3. 비밀번호와 비밀번호 확인 일치
         * 4. 휴대전화(선택사항) - 입력된 경우 형식 체크
         * 5. 휴대전화번호가 입력된 경우 숫자만 추출해서 다시 커맨드 객체에 저장
         * 6. 필수 약관 동의 체크
         */
    }
}

//TDD -> 설계를 기반으로 테스트를 진행한다.
