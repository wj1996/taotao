<%--
  Created by IntelliJ IDEA.
  User: 24253
  Date: 2019/3/5
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>导入商品数据到索引库</title>
</head>
<body>
    <div>
        <a href="javascript:void(0)" onclick="importall()" class="easyui-linkbutton">一键导入商品数据到索引库</a>
    </div>
    <script>
        function importall(){
            $.post("/index/importall",null,function(data){
                if(data.status == 200){
                    $.messager.alert("提示","商品数据导入成功");
                }else{
                    $.messager.alert("提示","商品数据导入失败");
                }
            });
        }
    </script>
</body>
</html>
