<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>レビューズジェム掲示板</title>
</head>
<body>

<h1>レビューズジェム掲示板へようこそ</h1>

<form action="Login" method="post">
ユーザー名:<input type="text" name="name"><br>
パスワード:<input type="password" name="pass"><br>
<input type="submit" value="ログイン">
</form>

 <a href="InsertUser">ユーザー登録へ</a>

<jsp:include page="WEB-INF/jsp/footer.jsp" />

</body>
</html>