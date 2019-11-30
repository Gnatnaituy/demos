<%@ page import="com.tjise.model.User" %><%--
  User: Administrator
  Date: 2019/4/27
  Time: 17:39
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>用户信息</title>
    <link rel="stylesheet" crossorigin="anonymous"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T">
</head>
<body>
<div class="container">
    <div class="panel panel-primary">
        <% User user = (User) session.getAttribute("user"); %>
        <div class="panel-heading">
            <h3 class="panel-title text-lg-center"><%=user.getUname()%>详细信息</h3>
        </div>
        <div class="panel-body">
            <table class="table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>密码</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>生日</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><%=user.getUid()%></td>
                    <td><%=user.getUname()%></td>
                    <td><%=user.getPwd()%></td>
                    <td><%=user.getSex()%></td>
                    <td><%=user.getAge()%></td>
                    <td><%=user.getBirth()%></td>
                </tr>
                </tbody>
            </table>
        </div>
        <form action="queryall" method="get" class="text-lg-center">
            <button type="submit" class="btn btn-primary">返回</button>
        </form>
    </div>
</body>
</html>
