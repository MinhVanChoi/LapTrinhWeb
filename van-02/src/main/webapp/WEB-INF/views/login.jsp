<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập tài khoản</title>
    <link rel="stylesheet" href="path/to/your/styles.css">
</head>
<body>
    <form action="LoginController" method="post">
        <h2>Đăng nhập tài khoản</h2>
        <c:if test="${alert != null}">
            <h3 class="alert alert-danger">${alert}</h3>
        </c:if>
        <section>
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <input type="username" placeholder="Tài khoản" name="username" class="form-control">
                </div>
            </label>
        </section>
        <section>
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                    <input type="password" placeholder="Mật khẩu" name="password" class="form-control">
                </div>
            </label>
        </section>
        <section>
            <button type="submit" class="btn btn-primary">Đăng nhập</button>
        </section>
    </form>
</body>
</html>
