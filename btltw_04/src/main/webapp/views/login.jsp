<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<body>
<form action="/btltw_04/login" method="post">
<c:if test="${alert !=null}">
<h3 class="alert alertdanger">${alert}</h3>
</c:if>

  <div class="container">
    <label for="uname"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="username" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>

    <button type="submit">Login</button>
    <label>
      <input type="checkbox" checked="checked" name="remember"> Remember me
    </label>
  </div>
  <div class="container" style="background-color:#f1f1f1">
		<a href="register.jsp" class="register">Register</a>    
<br>
    <span class="psw">Forgot <a href="forgotpassword.jsp">password?</a></span>
  </div>
</form>



</body>
</html>