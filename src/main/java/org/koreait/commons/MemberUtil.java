package org.koreait.commons;

import jakarta.servlet.http.HttpSession;
import org.koreait.commons.constants.Role;
import org.koreait.entities.Member;
import org.koreait.models.member.MemberInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberUtil {

    @Autowired
    private HttpSession session;

    //로그인 여부
    public boolean isLogin(){

        return getMember() != null;
    }

    //관리자 여부
    public boolean isAdmin(){

//        if(isLogin()) {
//            MemberInfo memberInfo = getMember();
//        }

        return isLogin() && getMember().getRoles() == Role.ADMIN;
    }

    public MemberInfo getMember(){
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");

        return memberInfo;
    }

    public Member getEntity(){

        if(isLogin()){
            Member member = new ModelMapper().map(getMember(), Member.class);
            return member;
        }

        return null;
    }
}
