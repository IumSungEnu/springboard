package org.koreait.commons.validators;

/**
 * 필수 데이터 검증하는 validator
 *
 */
public interface RequiredValidator {
    default void requiredCheck(String str, RuntimeException e){
        if(str == null || str.isBlank()){
            throw e;
        }
    }
}
