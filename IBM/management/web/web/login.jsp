<%--
  User: Administrator
  Date: 2019/4/27
  Time: 17:00
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<head>
    <title>登录</title>
    <link rel="stylesheet" crossorigin="anonymous"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T">
</head>

<body>
<div class="container text-lg-center">
    <form action="login" method="post">
        <div class="form-group">
            <label for="uname">用户名</label>
            <input type="text" class="form-control" id="uname" name="uname" placeholder="用户名">
        </div>
        <div class="form-group">
            <label for="pwd">密码</label>
            <input type="text" class="form-control" id="pwd" name="pwd" placeholder="密码">
        </div>
        <button type="submit" class="btn btn-primary">登录</button>
    </form>
    <a href="register.jsp" class="btn btn-primary">注册</a>
</div>
</body>

</html>
