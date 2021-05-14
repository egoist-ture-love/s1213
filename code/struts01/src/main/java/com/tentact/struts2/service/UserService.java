package com.tentact.struts2.service;

import com.tentact.struts2.dao.UserDao;
import com.tentact.struts2.pojo.User;

import java.util.List;

public class UserService {
   UserDao userDao = new UserDao();
    public String findUser(User user) {
        User user1 = userDao.findByNameToString(user);
        if(user1 != null){
            if(user1.getPassword().equals(user.getPassword())){
                return "正确";
            }else {
                return "用户密码错误";
            }
        }else {
            return "用户名错误";
        }
    }

    public User findByName(User user) {
        User user1=userDao.findByName(user);
        return user1;
    }

    public void add(User user) {
        userDao.add(user);
    }

    public List<User> findAll() {
        List<User> userList=userDao.findAll();
        return userList;
    }

    public void deleteById(Integer id) {
        userDao.deleteById(id);
        System.out.println("删除了1");
    }

    public User findById(Integer id) {
            User user1=userDao.findById(id);
            return user1;
    }

    public void updateUser(User newUser) {
        userDao.updateUser(newUser);
    }
}
