package club.viaxiz.community.controller;

import club.viaxiz.community.mapper.UserMapper;
import club.viaxiz.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: xizhong
 * @Date: 2020-4-10 19:49
 */
@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;


    @GetMapping("/")
    public String Index(HttpServletRequest request){
        Cookie cookies[]=request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }



        return "index";
    }

}
