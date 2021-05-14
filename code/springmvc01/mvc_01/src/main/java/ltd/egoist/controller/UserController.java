package ltd.egoist.controller;

import ltd.egoist.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2021/5/2 19:36
 * @Created by Lenovo
 */

/**
 * springmvc接收参数--
 *      1.参数=值&参数=值 的 formdate类型
 *              1.1接受单个参数----直接在参数中带 参数类型 和 参数名
 *              1.2接受对象参数【多个参数】
 *                  对象类型 pojo对象
 *                          Map对象  使用单独封装到List和Map必须加上@RequestParams注解   而且map里面不可有集合 里面value值都是String
 *      2.{参数名:参数值,参数名:参数值}--json类型
 *              1.保证pojo参数与json参数名一样
 *              2.使用@RequestBody注解
 *              3.必须引入Jackson依赖
 */

/**
 * 想使用Servlet的API直接在参数中添加即可
 */

/**
 * @RequestParam注解介绍: 参数名和方法参数名不一样时进行赋值
 *                         封装List Map列表
 */

/**
 * @RequestBody  1.post请求体信息
 *               2.获取json类型请求参数封装到pojo中
 */

/**
 * @PathVariable注解   restful风格的路径
 */

/**
 * @RequestHeadder(请求头参数名) 获取请求头信息
 */
/**
 * 向浏览器响应json字符串  @ResponseBody进行响应   return 想要响应的值   进入jackon
 */

/**
 * @CookieValue获取cookie的值--(cookie名字)
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/add")
    public String addPeople(String name,int age) {
        System.out.println("添加人"+name+",添加人年龄"+age);
        return "success";
    }
    @RequestMapping("/addUser")
    public String addUser(User user) {
        System.out.println(user);
        return "success";
    }
    @RequestMapping("/deleteAll")
    public String deleteAll(@RequestParam List<Integer> id){//加上RequestParam直接将参数放到list集合中
        System.out.println(id);
        return "success";
    }
    @RequestMapping("/paramsToMap")
    public String ToMap(@RequestParam Map map){
        System.out.println(map);
        return "success";
    }
    @RequestMapping("/serveletApi")
    public String ServeletApi(HttpSession session){
        session.setAttribute("username","张家硕");
        return "success";
    }
    @RequestMapping("/ui")
    public String RPannotion(@RequestParam("name") String username){
        System.out.println(username);
        return "success";
    }
    @PostMapping("/json")
    public String Tojson(@RequestBody User user){
        System.out.println("11111111111111111");
        System.out.println(user);
        return "success";
    }
    @PostMapping("/{id}")
    public String resu(@PathVariable("id") int id){
        System.out.println(id+"post");
        return "success";
    }
    @GetMapping("/{id}")
    public String resul(@PathVariable("id") int id){
        System.out.println(id+"get");
        return "success";
    }
    @RequestMapping("/t1")
    public String t1(@RequestHeader("user-Agent") String userAgent){
        System.out.println(userAgent);
        return "success";
    }
    @RequestMapping("/t2")
    public User t2(){
        User user = new User();
        user.setAge(11);
        user.setName("张三");
        user.setSex("男");
        user.setBirthday(new Date());
        return user;
    }

}
