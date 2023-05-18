package org.koreait.controllers.admins;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminMainController")  //충돌을 방지하기 위해 지정해준다.
@RequestMapping("/admin")
public class MainController {

    @GetMapping
    public String index(){
        return "admin/index";
    }
}
