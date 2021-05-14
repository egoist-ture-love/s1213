package com.tentact.struts2.dao;

import com.tentact.struts2.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    static List<User> userList = new ArrayList<>();
    static {
        userList.add(new User(1,"t1","1"));
        userList.add(new User(2,"t2","2"));
        userList.add(new User(3,"t3","3"));
    }

    public User findUser(User user) {
        for (User user1: userList){
            if(user1.getName().equals(user.getName())&&user1.getPassword().equals(user.getPassword())){
                return user;
            }
        }
        return null;
    }

    public User findByName(User user) {
        for(User user1:userList){
            if(user1.getName().equals(user.getName())){
                return null;
            }
        }
        return user;
    }

    public void add(User user) {
        Integer id = findId();
        userList.add(new User(id,user.getName(),user.getPassword()));
    }

    public Integer findId(){
        int temp = 0;
        for(User user1:userList){
            temp = user1.getId();
            temp++;
        }
        return temp;
    }

    public List<User> findAll() {
        return userList;
    }

    public User findByNameToString(User user) {
        for(User user1:userList){
            if(user1.getName().equals(user.getName())){
                return user1;
            }
        }
        return null;
    }

    public void deleteById(Integer id) {
        for (User user1: userList){
            if(user1.getId()==id){
                userList.remove(user1);
                System.out.println("删除了");
                return;
            }
        }

    }

    public User findById(Integer id) {
        for(User user1:userList){
            if(user1.getId()==id){
                return user1;
            }
        }
        return null;
    }

    public void updateUser(User newUser) {
        for(User user1:userList){
            if(user1.getId()==newUser.getId()){
                user1.setName(newUser.getName());
                user1.setPassword(newUser.getPassword());
                return;
            }
        }
    }
}
