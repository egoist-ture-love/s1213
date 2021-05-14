<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/4/1
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
    <form action="/login/first.action" method="post">
        用户名:<input type="text" name="username"><br>
        密码:<input type="password" name="password"><br>
        <input type="submit" value="登录"><br>
        <span>${msg}</span><br>
        <a href="/register.jsp">点击注册</a>
    </form>




    <form method="post" action="show/second.action">
        <input type="submit" value="查看">
    </form>

</body>
</html>
