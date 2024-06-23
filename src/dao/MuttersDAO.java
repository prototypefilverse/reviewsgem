package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Mutter;

public class MuttersDAO {

	// Connection型変数
	 private Connection connection;
	// ドライバー名を格納
	private static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// データベースの接続文字列
	private final String url = "jdbc:sqlserver://reviewsgembb.database.windows.net:1433;database=reviewsgembb;user=morikawasusumu@reviewsgembb;password=00830080gG;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

	public List<Mutter> findAll() {

		// returnするリストを用意
		List<Mutter> mutterList = new ArrayList<>();

		try {
            // JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);
            // DriverManagerデータベースに接続
			connection = DriverManager.getConnection(url);

			// String型変数にクエリ(問い合わせ)を用意
			// String sql = "SELECT id, name, text, post_date FROM mutters ORDER BY ID DESC limit 10";
			String sql = "SELECT TOP 10 id, name, text, post_date FROM mutters ORDER BY ID DESC";

			// SQLをDBに届けるPreparedStatementインスタンスを取得
			PreparedStatement ps = connection.prepareStatement(sql);

            // SELECTが実行され、ResultSetインスタンスに結果が格納される
			ResultSet rs = ps.executeQuery();

			// 結果（）に格納されたレコードの内容を表示
			// next() 対象レコードを一つ進める
			// 取得結果が存在するかどうかを表すbool値)で条件分岐や繰り返しを行い、ResultSetから情報を引き出す
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String text = rs.getString("text");
				Date postDate = rs.getTimestamp("post_date"); // getTimestamp()で投稿日時を取得（投稿時に自動で生成されている）
				// 取り出した値をコンストラクタに入れインスタンス生成
				Mutter mutter = new Mutter(id, name, text, postDate);
				// リストに格納
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // try

		return mutterList;

	} // findAll

	public List<Mutter> findAll_s() {

		// returnするリストを用意
		List<Mutter> mutterList = new ArrayList<>();

		try {
			Class.forName(DRIVER_NAME);
			connection = DriverManager.getConnection(url);

			// String型変数にクエリ(問い合わせ)を用意 IDでDESC順
			// String sql = "SELECT id, name, text, post_date FROM mutters ORDER BY ID DESC limit 100 offset 10";
			String sql = "SELECT id, name, text, post_date FROM mutters ORDER BY id DESC OFFSET 10 ROWS FETCH NEXT 100 ROWS ONLY";


			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			// rs.next()取得結果が存在するかどうかを表すbool値)で条件分岐や繰り返しを行い、ResultSetから情報を引き出す
			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("name");
				String text = rs.getString("text");
				Date postDate = rs.getTimestamp("post_date"); // getTimestamp()で投稿日時を取得（投稿時に自動で生成されている）
				Mutter mutter = new Mutter(id, name, text, postDate);
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // try

		return mutterList;

	} // findAll_s

	public boolean create(Mutter mutter) {

		try {
			Class.forName(DRIVER_NAME);
			connection = DriverManager.getConnection(url);

			// プレースホルダによるSQL分の組み立て
			// SQL文の中にクエスチョンマーク（？）の形でパラメータを埋め込んで仮のSQL文を作る
			// 利点・・・SQL文の見易さ・不正な検索を防止
			String sql = "INSERT INTO mutters(NAME , TEXT) VALUES(?,?)";

			PreparedStatement ps = connection.prepareStatement(sql);

			// SQLの文にあった(？)の位置に値を入れる
			// 第一引数・・・ (？)の場所 , 第二引数・・・値
			ps.setString(1, mutter.getUserName());
			ps.setString(2, mutter.getText());

			// SELECT文の際に使用したexecuteQuery()の戻り値は
			// 検索の結果が格納されているResultSetオブジェクト
			// executeUpdate()の戻り値は更新した行数(int)
			// INSERT文，UPDATE文，DELETE文の際に使用
			int result = ps.executeUpdate();

			// １行更新できたら
			if (result == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // try

		return false;

	}// create

	public void delete(int id) {

		try {
			Class.forName(DRIVER_NAME);
			connection = DriverManager.getConnection(url);

			String sql = "DELETE FROM mutters WHERE id = ? ";

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

} // class
