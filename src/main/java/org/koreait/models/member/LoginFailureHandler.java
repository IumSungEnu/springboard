package org.koreait.models.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler { //로그인 실패시 핸들러
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //데이터, 세션 불러오기
        HttpSession session = request.getSession();
        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");

        //초기화
        session.removeAttribute("userId");
        session.removeAttribute("requiredUserId");
        session.removeAttribute("requiredUserPw");
        session.removeAttribute("global");

        session.setAttribute("userId", userId);

        try{
            if(userId == null || userId.isBlank()){
                throw new LoginValidationException("requiredUserId", "NotBlank.userId");
            }
            if(userPw == null || userPw.isBlank()){
                throw new LoginValidationException("requiredUserPw", "NotBlank.userPw");
            }

            throw new LoginValidationException("global", "Validation.login.fail");  //전체 에러

        }catch (LoginValidationException e){
            session.setAttribute(e.getField(), e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/member/login");  //실패시에도 login페이지로 이동
    }
}
