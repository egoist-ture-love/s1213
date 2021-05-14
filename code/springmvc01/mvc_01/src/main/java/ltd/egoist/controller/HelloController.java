package ltd.egoist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Classname HelloController
 * @Description TODO
 * @Date 2021/5/2 19:00
 * @Created by Lenovo
 */

/**
 * 我们的目标在访问http://localhost8080/hello/sayHello----就可以处理请求相应到/WEB-INF/pages/success.jsp页面中
 * 1.配置spring核心容器--配置spring包扫描 和 mvc加载注解驱动
 * 2.在web.xml配置DispatcherServlet---让DispatcherServlet来接收请求
 * 3.配置视图解析器,让他来使success生效
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/sayHello")
    public String sayHello(){
        System.out.println("Hello world");
        //通过请求转发进行跳转
        return "success";
    }
//    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @PostMapping("/add")
    public String add(){
        System.out.println("执行add方法");
        return "success";
    }

    /**
     * requestMapping注解介绍： requestMapping使用位置   1.Controller类上  2.方法上
     * 属性: path value---->映射路径 属性写不写都一样
     * method请求方法
     * 但是可以使用 PostMapping这种注解直接访问
     */
}
