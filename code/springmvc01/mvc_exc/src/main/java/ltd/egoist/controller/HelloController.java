package ltd.egoist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname HelloController
 * @Description TODO
 * @Date 2021/5/4 16:41
 * @Created by Lenovo
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/sayhello")
    public String sayHello(){
        System.out.println("你好世界");
        int i = 1/0;
        return "success";
    }
}
