<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Đăng kí</title>
</head>
<body>
<form action="register" style="border:1px solid #ccc" method="post">
  <div class="container">
  		<c:if test="${alert !=null}">
		<h3 class="alert alert-danger">${alert}</h3>
</c:if>
<section> </section>
    <h1>Register</h1>
    <p>Điền vào ok ?.</p>
    <hr>

    <label for="username"><b>UserName</b></label>
    <input type="text" placeholder="Enter Username" name="username" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>

    <label for="email"><b>Email</b></label>
    <input type="text" placeholder="Enter Email" name="email" required>

    <label for="email"><b>Full Name</b></label>
    <input type="text" placeholder="Enter your full name" name="fullname" required>

    <label for="email"><b>Phone</b></label>
    <input type="text" placeholder="Enter your phone" name="phone" required>


    <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

    <div class="clearfix">
      <a href="login.jsp"><button type="button" class="cancelbtn">Cancel</button> </a>
      <button type="submit" class="register">Register</button>
    </div>
  </div>

</form>


</body>
</html>