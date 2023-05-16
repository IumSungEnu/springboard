package org.koreait.commons.validators;

public interface PasswordValidator {

    /**
     * 비밀번호 복잡성 체크 - 알파벳 체크
     *
     * @param password
     * @param caseIncentive
     *      false: 소문자 + 대문자가 반드시 포함되는 패턴
     *      true : 대소문자 상관없이 포함되는 패턴
     * @return
     */

    default boolean alphaCheck(String password, boolean caseIncentive){
        //알파벳 대,소문자 구분 - 기본값(false) -> 대,소문자가 각각 1개씩 들어간다.
        // ture일 경우 -> 대,소문자 구분없이 한번만 들어간다.

        if(caseIncentive){ //대소문자 구분없이 체크
            return password.matches("[a-zA-Z]+");
            // + 는 한(개)자 이상
        }

        // 대문자, 소문자 각각 체크
        return password.matches("[a-z]+") && password.matches("[A-Z]+");
    }

    /**
     * 숫자가 포함된 패턴인지 체크
     * 
     * @param password
     * @return
     */
    default boolean numberCheck(String password){
        
        return password.matches("\\d+");  //[0-9]+
    }

    /**
     * 특수문자가 포함된 패턴인지 체크
     * 
     * @param password
     * @return
     */
    default boolean specialCharsCheck(String password){
        
        return password.matches("[`~!#$%^&*()-_+=]+");
    }
}
