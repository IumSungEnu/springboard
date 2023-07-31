package org.koreait.controllers.mains;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainIndexController {

    @GetMapping("/")
    public String index(){

        return "main/index";
    }

    @GetMapping("/aboutme")
    public String aboutme(){

        return "main/aboutme";
    }

    @GetMapping("/skills")
    public String skills(){

        return "main/skills";
    }

    @GetMapping("/community")
    public String community(){

        return "main/community";
    }
}
