package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class UsersDAO {

	// Connection型変数
	 private Connection connection;
	// ドライバー名を格納
	private static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// データベースの接続文字列
	private final String url = "jdbc:sqlserver://reviewsgembb.database.windows.net:1433;database=reviewsgembb;user=morikawasusumu@reviewsgembb;password=00830080gG;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

	public boolean isUserValid(String name, String pass){

		// 結果を返す変数の用意
		boolean result = false;

		try {
			// JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);

			// （お約束）MAMPで用意したデータベースに接続
			connection = DriverManager.getConnection(url);

			//SQL文   name（ユニーク）カラムのうち、引数（パラメータ）のnameとpassと一致するレコードを探す文の？つき
			String sql = "SELECT name FROM users WHERE name=? AND pass=?";

			// SQLをDBに届けるPreparedStatementインスタンスを取得
			PreparedStatement ps = connection.prepareStatement(sql);
			// そのPreparedStatementインスタンスに引数の値をいれて完成
			ps.setString(1,name);
			ps.setString(2,pass);

			// SQLの実行 ResultSetインスタンスに結果が格納される
			ResultSet res = ps.executeQuery();

			// idとパスワードが一致しているユーザーが存在していた場合、res.next()がtrueになる
			if (res.next()) {
				result = true;
			 } else {
				result = false;
			 }
	       	} catch (SQLException e) {
			  e.printStackTrace();
	     	} catch (ClassNotFoundException e) {
				throw new IllegalStateException("JDBCドライバを読み込めませんでした");
			}

		    return result;

	 }

	public void insertUser(User user)  {

		// 静的SQL文を実行し、作成された結果を返すために使用されるオブジェクトの宣言
		Statement  smt = null;

		// JDBCドライバを読み込み
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		//SQL文
		String sql = "INSERT INTO users VALUES('"
		                     + user.getName()   + "','"
				             + user.getPass()    + "')" ;

		  try{
			  // (お約束？)MAMPで用意したデータベースに接続
			  connection = DriverManager.getConnection(url);
			  // SQL文をデータベースに送るためのcreateStatement()
	          smt = connection.createStatement();
		      //SQLをDBへ発行
              smt.executeUpdate(sql);

		         }catch(Exception e){
		        	 e.printStackTrace();
		 			throw new IllegalStateException("登録できませんでした。同名のユーザーが存在します");
		          }
		         }

	}

