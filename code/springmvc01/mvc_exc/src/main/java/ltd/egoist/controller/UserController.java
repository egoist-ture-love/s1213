package ltd.egoist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2021/5/4 16:41
 * @Created by Lenovo
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/hello")
    public String sayHello(){
        System.out.println("用户:你好世界");
        int i = 1/0;
        return "success";
    }
}
