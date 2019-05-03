<%@ page import="com.tjise.model.User" %>
<%@ page import="java.util.List" %>
<%--
  User: Administrator
  Date: 2019/4/27
  Time: 14:11
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<head>
    <title>所有用户信息</title>
    <link rel="stylesheet" crossorigin="anonymous"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T">
</head>

<body>
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title text-lg-center">用户信息列表</h3>
        </div>
        <div class="panel-body text-lg-center">
            <table class="table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>密码</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>生日</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <% for (User user : (List<User>) session.getAttribute("users")) { %>
                    <tr>
                        <td><%=user.getUid()%></td>
                        <td><%=user.getUname()%></td>
                        <td><%=user.getPwd()%></td>
                        <td><%=user.getSex()%></td>
                        <td><%=user.getAge()%></td>
                        <td><%=user.getBirth()%></td>
                        <td>
                            <a href="userinfo?uid=<%=user.getUid()%>" class="btn btn-primary">查看信息</a>
                            <a href="updateshow?uid=<%=user.getUid()%>" class="btn btn-info">修改</a>
                            <a href="delete?uid=<%=user.getUid()%>" class="btn btn-danger">删除</a>
                        </td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <div class="footer text-lg-center">
            <a href="register.jsp" class="btn btn-primary">添加新用户</a>
            <a href="logout" class="btn btn-primary">退出登录</a>
        </div>
    </div>

</div>
</body>
</html>
