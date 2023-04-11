<%--
  Created by IntelliJ IDEA.
  User: fangkai
  Date: 2023/4/11
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Response</title>
    <script src="js/jquery.min.js"></script>
    <script>
        //  页面加载，绑定单击时间
        $(function (){
           $("#btn").click(function () {
               $.ajax({
                   url:"user/testAjax",
                   contentType : "application/json;charset=utf8",
                   data:'{"username":"tt","password":"123","age":30}',
                   dataType:"json",
                   type:"post",
                   success:function (data) {
                        alert(data);
                        alert(data.username);
                        alert(data.password);
                   }
               });
           });
        });
    </script>
</head>
<body>
    <button id="btn">发送ajax请求</button>
</body>
</html>
