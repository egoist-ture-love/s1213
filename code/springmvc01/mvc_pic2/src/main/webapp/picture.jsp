<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/5/4
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/file/pic" method="post" enctype="multipart/form-data">
        请选择图片:<input type="file" name="picture"><br/>
        图片的描述:<input type="text" name="desc"><br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>
