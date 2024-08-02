<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Mutter, java.util.List" %>

<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User)session.getAttribute("loginUser");
// リクエストスコープに保存されたつぶやきリストを取得
List<Mutter> mutterList_s = (List<Mutter>)request.getAttribute("mutterList_s");
// リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String)request.getAttribute("errorMsg");

%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>レビューズジェム掲示板</title>
<link rel="stylesheet" type="text/css" href="styles/styles.css">
</head>
<body>

<div class="container">
  <h2 class="center-text">レビューズジェム掲示板 過去ログ（100件まで）</h2>

  <p class="user-info">
    <%= loginUser.getName() %>さん、ログイン中
    <a href="Logout" class="link">ログアウト</a>
  </p>

  <p><a href="Main_s" class="link">更新</a></p>

  <div class="mutter-list">
  for文
  <% for (int i = 0; i < mutterList_s.size(); i++) { %>
<div class="mutter-item">
  <span class="mutter-dete">投稿日時：<%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mutterList_s.get(i).getPostDate()) %></span>
  <span class="mutter-user">　<%= mutterList_s.get(i).getUserName() %></span>
  <p class="mutter-text"><%= mutterList_s.get(i).getText() %></p>

      ログインユーザーとユーザー名（ユニークカラム）が一致するつぶやきにだけ削除ボタンが表示される
      <% if(mutterList_s.get(i).getUserName().equals(loginUser.getName())) { %>
        onsubmit属性により、ボタンを押すと下に用意してる confirmDelete() がreturnで発火する
        <form action="Delete" method="post" onsubmit="return confirmDelete();" class="delete-form">
          hiddenでつぶやきのidと、識別名confirmedの"true"文字列を送る
          <input type="hidden" name="id" value="<%= mutterList_s.get(i).getId() %>">
          <input type="hidden" name="confirmed" value="true">
          <input type="submit" value="削除" class="btn btn-delete">
        </form>
      <% } %>
    </div>
  <% } %>
  </div>
</div>

削除の時用のスクリプト文
<script>
function confirmDelete() {
    return confirm("本当にこのつぶやきを削除しますか？");
}
</script>

共通フッター
<jsp:include page="footer.jsp" />

</body>
</html> --%>

