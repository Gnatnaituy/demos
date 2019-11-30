<%--
  User: Administrator
  Date: 2019/4/27
  Time: 15:11
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<head>
    <title>注册</title>
    <link rel="stylesheet" crossorigin="anonymous"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T">
</head>

<body>
    <div class="container">
        <form action="register" method="post">
            <div class="form-group">
                <label for="uname">用户名</label>
                <input type="text" class="form-control" id="uname" name="uname" placeholder="用户名">
            </div>
            <div class="form-group">
                <label for="pwd">密码</label>
                <input type="text" class="form-control" id="pwd" name="pwd" placeholder="密码">
            </div>
            <div class="form-group">
                <label for="sex">性别</label>
                <input type="text" class="form-control" id="sex" name="sex" placeholder="性别">
            </div>
            <div class="form-group">
                <label for="age">年龄</label>
                <input type="text" class="form-control" id="age" name="age" placeholder="年龄">
            </div>
            <div class="form-group">
                <label for="birth">生日</label>
                <input type="date" class="form-control" id="birth" name="birth" placeholder="生日">
            </div>
            <button type="submit" class="btn btn-primary">
                <% if (session.getAttribute("logineduser") == null) { %> 注册
                <% } else { %> 添加 <% } %>
            </button>
        </form>
    </div>
</body>

</html>
