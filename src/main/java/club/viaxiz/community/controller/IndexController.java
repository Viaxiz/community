package club.viaxiz.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

/**
 * @Author: xizhong
 * @Date: 2020-4-10 19:49
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String Index(){
        return "index";
    }

}
