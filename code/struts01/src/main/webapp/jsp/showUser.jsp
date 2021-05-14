<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tentact.struts2.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/4/6
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
<%--    <script src="../js/jquery-3.3.1.min.js" type="text/javascript"></script>--%>
</head>
<body>
    <a href="/demo/demo_helloWorld.action">11111</a>
    <a href="/demo/demo_add.action">22222</a>
    <table border="1px" width="500px" cellpadding="4px" cellspacing="0">
        <thead>
            <th>用户编号:</th>
            <th>用户姓名:</th>
            <th>用户密码:</th>
            <th>功能:</th>
        </thead>
        <c:forEach items="${userList}"  var="user" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${user.name}</td>
                <td>${user.password}</td>
                <td>
                    <input type="button" value="删除" onclick="fn1(${user.id},'${user.name}')">
                    <input type="button" value="修改" onclick="fn2(${user.id})">
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
    <script>
        function fn1(id,name) {
            var b = confirm("您是否要删除"+name+"?");
            if(b){
                location.href="http://localhost:8080/show/first.action?id="+id;
            }
        }
        function fn2(id) {
            location.href="http://localhost:8080/show/third.action?id="+id;
        }
    </script>
</html>
