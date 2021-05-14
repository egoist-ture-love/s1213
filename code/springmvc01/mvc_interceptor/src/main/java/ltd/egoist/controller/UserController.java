package ltd.egoist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2021/5/4 17:09
 * @Created by Lenovo
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/hello")
    public String getName(){
        System.out.println("你好世界");
        return "success";
    }
}
