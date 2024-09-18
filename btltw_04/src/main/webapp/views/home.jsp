<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="http://localhost:5740/btltw_04/views/home.css">
    <title>Trang Người Dùng</title>
</head>
<body>
    <header>
        <h1>Chào mừng, ${username}!</h1>
    </header>
    <main>
        <div class="user-info">
            <p><strong>Email:</strong> ${email}</p>
            <p><strong>Họ và Tên:</strong> ${fullname}</p>
            <p><strong>Số Điện Thoại:</strong> ${phone}</p>
        </div>
        <div class="logout">
            <a href="${pageContext.request.contextPath}/logout" class="logout-button">Đăng xuất</a>
        </div>
    </main>
</body>
</html>