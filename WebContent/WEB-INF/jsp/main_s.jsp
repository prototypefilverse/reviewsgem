<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<html>
<head>
<meta charset="UTF-8">
<title>レビューズジェム掲示板</title>
</head>
<body>

<h2>過去ログ（100件まで）</h2>

<p>
<%= loginUser.getName() %>さん、ログイン中
<a href="Logout">ログアウト</a>
</p>

<p><a href="Main_s">更新</a></p>


<%-- for文 --%>
<% for (int i = 0; i < mutterList_s.size(); i++) { %>
 <p>----------------------------------------<p>
  <p><%= mutterList_s.get(i).getUserName() %> : 投稿日時: <%= mutterList_s.get(i).getPostDate() %><br>
     <%= mutterList_s.get(i).getText() %></p>

  <%-- ログインユーザーとユーザー名（ユニークカラム）が一致するつぶやきにだけ削除ボタンが表示される --%>
  <% if(mutterList_s.get(i).getUserName().equals(loginUser.getName())) { %>
      <%-- onsubmit属性により、ボタンを押すと下に用意してる confirmDelete() がreturnで発火する --%>
    <form action="Delete" method="post" onsubmit = "return confirmDelete();">
    <%-- hiddenでつぶやきのidと、識別名confirmedの"true"文字列を送る --%>
    <input type="hidden" name="id" value="<%= mutterList_s.get(i).getId() %>">
    <input type="hidden" name="confirmed" value="true">
    <input type="submit" value="このつぶやきを削除">
    </form>

  <% } %>
<% } %>



<%-- 削除の時用のスクリプト文 --%>
<script>
function confirmDelete() {
    return confirm("本当にこのつぶやきを削除しますか？");
}
</script>

<%-- 共通フッター --%>
<jsp:include page="footer.jsp" />

</body>
</html>