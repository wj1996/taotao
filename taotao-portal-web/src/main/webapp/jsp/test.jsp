<%--
  Created by IntelliJ IDEA.
  User: wj
  Date: 2019/3/28
  Time: 8:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../js/jquery-1.6.4.js" />
    <script>
        $(function(){
            $.ajax({
                url:"http://localhost:9096/js/test.json",
                type:"get",
                dataType:"json",
                success:function(data){
                    alert("success");
                }
            });
        })
    </script>
</head>
<body>
    123
</body>
</html>
