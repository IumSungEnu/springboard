package org.koreait.controllers.members;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.models.member.MemberSaveService;
import org.modelmapper.internal.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberSaveService saveService;

    @GetMapping("/join")
    public String join(){

        return "member/join";
    }

    @PostMapping("/join")
    public String joinPs(@Valid JoinForm joinForm, Errors errors){ //가입처리

        if(errors.hasErrors()){
            return "member/join";
        }

        saveService.save(joinForm);

        return "redirect:/member/login";
    }
}
