<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/4/13
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="show/forth.action?id=${user.id}" method="post">
    名字:<input type="text" value="${user.name}" name="name"><br>
    密码<input type="text" value="${user.password}" name="password"><br>
    <input type="submit" value="提交">
</form>

</body>
</html>
