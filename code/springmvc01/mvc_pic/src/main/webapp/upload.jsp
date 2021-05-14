<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/5/4
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/file/pic" method="post" enctype="multipart/form-data">
        图片： <input type="file" name="upload"/><br/>
        图片描述:<input type="text" name="pdesc"/>
        <input type="submit" value="上传"/>
    </form>
</body>
</html>
