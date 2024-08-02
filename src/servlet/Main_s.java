//package servlet;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//
//
//@WebServlet("/Main_s")
//public class Main_s extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//
//		// つぶやきリストを取得して、リクエストスコープに保存
//		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
//		List<Mutter> mutterList_s = getMutterListLogic.execute_s();
//		request.setAttribute("mutterList_s", mutterList_s );
//
//		// ログインしているか確認するため
//		// セッションスコープからユーザー情報を取得
//		HttpSession session = request.getSession();
//		User loginUser = (User)session.getAttribute("loginUser");
//
//		if(loginUser == null){  // ログインしていない場合
//			// リダイレクト
//			response.sendRedirect("index.jsp");
//		} else {  //ログイン済みの場合
//			// フォワード
//		    RequestDispatcher dispatchar = request.getRequestDispatcher("WEB-INF/jsp/main_s.jsp");
//		    dispatchar.forward(request,response);
//		}
//	}
//
//}
