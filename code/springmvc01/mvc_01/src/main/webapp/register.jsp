<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/5/2
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/user/addUser" method="post">
        姓名:<input type="text" name="name"><br/>
        年龄:<input type="text" name="age"><br/>
        性别:<input type="text" name="sex"><br/>
        出生年月:<input type="text" name="birthday"><br/>
        爱好: 篮球<input type="checkbox" value="bb" name="hobbies">
        足球<input type="checkbox" value="fb" name="hobbies">
        羽毛球<input type="checkbox" value="yb" name="hobbies"><br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>
