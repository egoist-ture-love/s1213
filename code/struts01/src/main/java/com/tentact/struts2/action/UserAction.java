package com.tentact.struts2.action;

import com.tentact.struts2.pojo.User;
import com.tentact.struts2.service.UserService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserAction {
    //返回值固定String类型,
    UserService userService = new UserService();

    public String execute() {
        return "success";
    }

    public String login() throws IOException, ParseException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username, password);

        String msg = userService.findUser(user);
        request.setAttribute("msg", msg);
        if (msg.equals("正确")) {

            Calendar cal = Calendar.getInstance();
            int i = cal.get(Calendar.HOUR);
            request.getSession().setAttribute("user",user);
            if(i >= 2 && i < 4){
                request.getSession().removeAttribute("user");
            }

            return "success";
        } else {
            return "fail";
        }
    }
    public void register() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username, password);
        //先判断用户名是否存在
        User user1 = userService.findByName(user);
        if (user1 == null) {
            throw new RuntimeException("用户存在");
        } else {
            userService.add(user);
            response.sendRedirect("/jsp/register.jsp");
        }

    }

    public String showUser() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        //查找到UserList
        List<User> userList = userService.findAll();
        //传入request域对象
        request.setAttribute("userList", userList);
        return "success";
    }

    public String deleteUser() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Integer id = Integer.valueOf(request.getParameter("id"));
        System.out.println(id);
        userService.deleteById(id);
        System.out.println("删除成功");
        return "success";
    }

    public String upDateUser() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Integer id = Integer.valueOf(request.getParameter("id"));
        User user1 = userService.findById(id);
        request.setAttribute("user", user1);
        return "success";
    }

    public String upDateUser1() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Integer id = Integer.valueOf(request.getParameter("id"));
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        System.out.println(id);
        System.out.println(password);
        System.out.println(name);
        User newUser = new User(id, name, password);
        userService.updateUser(newUser);
        return "success";
    }


}
