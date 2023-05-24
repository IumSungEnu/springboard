package org.koreait.commons.validators;

public interface LengthValidator {

    /**
     *
     * @param str
     * @param min 최소 문자 갯수 -> 0일때는 체크 x
     * @param max 최대 문자 갯수 -> 0일때는 체크 x
     */

    // 문자열
    // 최대
    default void lengthCheck(String str, int min, int max, RuntimeException e){
        if(str == null || (min > 0 && str.length() < min) || (max > 0 && str.length() > max)){
            throw e;
        }
    }
    // 최소
    default void lengthCheck(String str, int min, RuntimeException e){
        lengthCheck(str, min, 0, e);
    }

    //숫자의 범위
    default void lengthCheck(long num, int min, int max, RuntimeException e){
        // 0 ~ -1
        if(num < min || num > max){
            throw e;
        }
    }

    default void lengthCheck(long num, int min, RuntimeException e){
        if(num < min){
            throw e;
        }
    }
}
