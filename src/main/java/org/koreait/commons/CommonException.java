package org.koreait.commons;

import org.springframework.http.HttpStatus;

import java.util.ResourceBundle;

/**
 * 공통 예외
 * 
 */
public class CommonException extends RuntimeException{
    //protected -> 외부에서 상속받았다.
    protected static ResourceBundle bundleValidation;  //메세지 코드
    protected static ResourceBundle bundleError;       //메세지 코드
    
    protected HttpStatus httpStatus;  //상태코드        //응답 코드

    static {
        bundleValidation = ResourceBundle.getBundle("messages.validations");
        bundleError = ResourceBundle.getBundle("messages.errors");
    }
    
    public CommonException(String message){
        super(message);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public CommonException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
    
    public HttpStatus getStatus(){
        return httpStatus;
    }
}
