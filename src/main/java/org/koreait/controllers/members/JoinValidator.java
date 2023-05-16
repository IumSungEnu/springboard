package org.koreait.controllers.members;

import lombok.RequiredArgsConstructor;
import org.koreait.commons.validators.MobileValidator;
import org.koreait.commons.validators.PasswordValidator;
import org.koreait.repositories.MemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator, MobileValidator, PasswordValidator {

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

        JoinForm joinForm = (JoinForm) target;
        //재료를 가지고 온다(데이터)
        String userId = joinForm.getUserId();
        String userPw = joinForm.getUserPw();
        String userPwRe = joinForm.getUserPwRe();
        String mobile = joinForm.getMobile();
        //email -> bean 객체로 부여했기 때문에 굳이 쓸 필요 x
        boolean[] agrees = joinForm.getAgrees();  //필수 약관

        // 1. 아이디 중복 여부
        if(userId != null && userId.isBlank() && memberRepository.exists(userId)){
            errors.rejectValue("userId", "Validation.duplicate.userId");
        }

        // 2. 비밀번호 복잡성 체크(알파벳 - 대,소문자 , 숫자, 특수문자)
        if(userPw != null && !userPw.isBlank()
            && (!alphaCheck(userPw, false)
                || !numberCheck(userPw)
                || !specialCharsCheck(userPw))){

            errors.rejectValue("userPw", "Validation.complexity.password");
        }

        // 3. 비밀번호와 비밀번호 확인 일치
        if(userPw != null && !userPw.isBlank() &&
                userPwRe != null && !userPwRe.isBlank() && !userPw.equals(userPwRe)){
            errors.rejectValue("userPwRe", "Validation.incorrect.userPwRe");
        }

        // 4. 휴대전화(선택사항) - 입력된 경우 형식 체크
        // 5. 휴대전화번호가 입력된 경우 숫자만 추출해서 다시 커맨드 객체에 저장
        if(mobile != null && !mobile.isBlank()){
            if(!mobileNumCheck(mobile)){
                errors.rejectValue("mobile", "Validation.mobile");
            }
            mobile = mobile.replaceAll("\\D", "");
            joinForm.setMobile(mobile);  //숫자만 db에 저장
        }

        // 6. 필수 약관 동의 체크
        if(agrees != null && agrees.length > 0){
            for(boolean agree : agrees){
                if(!agree){
                    errors.reject("Validation.joinForm.agree");
                    break;
                }
            }
        }
    }
}

//TDD -> 설계를 기반으로 테스트를 진행한다.