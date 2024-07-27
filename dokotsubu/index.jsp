<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>レビューズジェム掲示板</title>
<link rel="stylesheet" type="text/css" href="styles/styles.css">
</head>
<body>

<div class="container">
<h1>レビューズジェム掲示板へようこそ</h1>

<form action="Login" method="post">
    <div class="input-group">
        <label>ユーザー名:</label>
        <input type="text" name="name" required>
    </div>
    <div class="input-group">
        <label>パスワード:</label>
        <input type="password" name="pass" required>
    </div>
    <input type="submit" value="ログイン" class="btn">
</form>

<a href="InsertUser" class="link">ユーザー登録へ</a>
</div>

<jsp:include page="WEB-INF/jsp/footer.jsp" />

</body>
</html>








