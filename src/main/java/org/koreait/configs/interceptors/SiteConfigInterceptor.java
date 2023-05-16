package org.koreait.configs.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 사이트 설정 유지
 *
 */
@Component
public class SiteConfigInterceptor implements HandlerInterceptor {
    //공통 부분
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        request.setAttribute("cssJsVersion", 1);

        return true;
    }
}

/**
 * 인터셉터 - 공통기능(3가지)
 * boolean preHandle() - 공통기능 + 통제기능(주로 인증 - 관리자 페이지, 마이페이지 등에서 사용)
 * 
 * void postHandle() - 
 * void afterCompletion() - 
 */

//다형성 -> 객체간의 유연성