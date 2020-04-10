package club.viaxiz.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = true, defaultValue = "World")
                                   String name, Model model) {
        model.addAttribute("name",name);
        return "greeting";
    }

    @PostMapping("/hello")
    public String hello(@RequestParam(name = "na")String name, Model model){
        model.addAttribute("na",name);
        return "hello";
    }


}