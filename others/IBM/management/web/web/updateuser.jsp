<%@ page import="com.tjise.model.User" %><%--
  User: Administrator
  Date: 2019/4/27
  Time: 15:11
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<head>
    <title>修改信息</title>
    <link rel="stylesheet" crossorigin="anonymous"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T">
</head>

<body>
<div class="container">
    <form action="updatedo" method="post">
        <% User user = (User) session.getAttribute("user"); %>
        <input type="hidden" name="uid" value="<%=user.getUid()%>">
        <div class="form-group">
            <label for="uname">用户名</label>
            <input type="text" class="form-control" id="uname" name="uname" value="<%=user.getUname()%>">
        </div>
        <div class="form-group">
            <label for="pwd">密码</label>
            <input type="text" class="form-control" id="pwd" name="pwd" value="<%=user.getPwd()%>">
        </div>
        <div class="form-group">
            <label for="sex">性别</label>
            <input type="text" class="form-control" id="sex" name="sex" value="<%=user.getSex()%>">
        </div>
        <div class="form-group">
            <label for="age">年龄</label>
            <input type="text" class="form-control" id="age" name="age" value="<%=user.getAge()%>">
        </div>
        <div class="form-group">
            <label for="birth">生日</label>
            <input type="date" class="form-control" id="birth" name="birth" value="<%=user.getBirth()%>">
        </div>
        <button type="submit" class="btn btn-info">保存</button>
    </form>
</div>
</body>

</html>
