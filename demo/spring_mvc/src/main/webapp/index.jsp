<%--
  Created by IntelliJ IDEA.
  User: fangkai
  Date: 2023/4/10
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>入门程序</h3>
<%--    <a href="anno/testCookieValue">入门程序</a>--%>
<%--    <form action="anno/testModelAttribute" method="post">--%>
<%--    <form action="anno/testModelAttribute2" method="post">--%>
<%--        用户名称：<input type="text" name="username"><br/>--%>
<%--        用户年龄<input type="text" name="age"><br/>--%>
<%--        <input type="submit" value="提交"><br/>--%>
<%--    </form>--%>
<%--    <a href="user/testForwardOrRedirect">入门程序</a>--%>
    <form action="user/uploadFile" method="post" enctype="multipart/form-data">
        选择文件<input type="file" name="upload"/></br>
        <input type="submit" value="上传"/></br>
    </form>
</body>
</html>
