<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="http://localhost:5740/btltw_04/views/register.css"> 
</head>
<body>
    <section>
        <div class="form-box">
            <div class="form-value">
                <form action="register" method="post">
                    <h2>Đăng Ký</h2>
                    <c:if test="${alert != null}">
                        <h3 class="alert alert-danger" aria-live="assertive">${alert}</h3>
                    </c:if>

                    <div class="inputbox">
                        <input type="text" name="username" required>
                        <label for="username">UserName</label>
                    </div>

                    <div class="inputbox">
                        <input type="password" name="password" required>
                        <label for="psw">Mật khẩu</label>
                    </div>

                    <div class="inputbox">
                        <input type="email" name="email" required>
                        <label for="email">Email</label>
                    </div>

                    <div class="inputbox">
                        <input type="text" name="fullname" required>
                        <label for="fullname">Họ và Tên</label>
                    </div>

                    <div class="inputbox">
                        <input type="text" name="phone" required>
                        <label for="phone">Số điện thoại</label>
                    </div>

                    <p>Bằng cách tạo tài khoản, bạn đồng ý với <a href="#" style="color:dodgerblue">Điều khoản & Quyền riêng tư</a>.</p>

                    <div class="clearfix">
                        <a href="login.jsp">
                            <button type="button" class="cancelbtn">Hủy</button>
                        </a>
                        <button type="submit" class="register">Đăng Ký</button>
                    </div>
                </form>
            </div>
        </div>
    </section>
</body>
</html>
