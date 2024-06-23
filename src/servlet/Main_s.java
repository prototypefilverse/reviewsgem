package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMutterListLogic;
import model.Mutter;
import model.User;


@WebServlet("/Main_s")
public class Main_s extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// つぶやきリストを取得して、リクエストスコープに保存
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList_s = getMutterListLogic.execute_s();
		request.setAttribute("mutterList_s", mutterList_s );
		
		// ログインしているか確認するため
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		
		if(loginUser == null){  // ログインしていない場合
			// リダイレクト
			response.sendRedirect("index.jsp");
		} else {  //ログイン済みの場合
			// フォワード
		    RequestDispatcher dispatchar = request.getRequestDispatcher("WEB-INF/jsp/main_s.jsp");
		    dispatchar.forward(request,response);
		}
	}
	
	

}
