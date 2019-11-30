<%--
  User: Administrator
  Date: 2019/4/27
  Time: 17:12
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="zh-CN">
<head>
    <title>提示信息</title>
    <link rel="stylesheet" crossorigin="anonymous"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T">
</head>
<body>
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">提示信息</h3>
        </div>
        <div class="panel-body">
            <%=session.getAttribute("info")%>
        </div>
        <div class="panel-footer">
            <% if ("登录成功!".equals(session.getAttribute("info"))) { %>
            <a href="queryall" class="btn btn-primary">查看信息列表</a>
            <% } else if ("密码错误!".equals(session.getAttribute("info"))) { %>
            <a href="login.jsp" class="btn btn-warning">重新登录</a>
            <% } else if ("请先登录!".equals(session.getAttribute("info"))){ %>
            <a href="login.jsp" class="btn btn-warning">登录</a>
            <% } else if ("注册成功!".equals(session.getAttribute("info"))){ %>
            <a href="login.jsp" class="btn btn-primary">登录</a>
            <% } else { %>
            <a href="register.jsp" class="btn btn-warning">注册</a>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
