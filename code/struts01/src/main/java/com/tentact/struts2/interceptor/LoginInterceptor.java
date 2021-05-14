package com.tentact.struts2.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import java.util.Map;

/**
 * 拦截器
 */
//过滤器可以过滤任意资源,拦截器只能拦截action,在方法执行之前拦截
public class LoginInterceptor extends MethodFilterInterceptor {
    //拦截方法
    @Override
    protected    String doIntercept(ActionInvocation actionInvocation) throws Exception {
        Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
        Object user = session.get("user");
        System.out.println(user);
        if(user == null){
            System.out.println("请先登录");
        }else {
            return actionInvocation.invoke();
        }
        return "fail";
    }
}
