<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="http://localhost:5740/btltw_04/views/login.css">
    <title>Đăng nhập</title>
</head>
<body>
    <section>
        <div class="form-box">
            <div class="form-value">
                <form action="/btltw_04/login" method="post">
                    <h2>Đăng Nhập</h2>
                    <div class="inputbox">
                        <ion-icon name="mail-outline"></ion-icon>
                        <input type="text" name="username" required>
                        <label>Username</label>
                    </div>
                    <div class="inputbox">
                        <ion-icon name="lock-closed-outline"></ion-icon>
                        <input type="password" name="password" required>
                        <label>Mật khẩu</label>
                    </div>
                    <div class="forget">
                        <label><input type="checkbox" name="remember">Nhớ tài khoản <a href="forgotpassword.jsp">Quên mật khẩu?</a></label>
                    </div>
                    <button type="submit">Đăng nhập</button>
                    <div class="register">
                        <p>Chưa có tài khoản? <a href="register.jsp">Đăng ký</a></p>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>