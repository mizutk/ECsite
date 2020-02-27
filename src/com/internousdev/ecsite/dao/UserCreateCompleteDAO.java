package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;//DBエラー等の情報を提供するための例外

import com.internousdev.ecsite.util.DBConnector;
import com.internousdev.ecsite.util.DateUtil;

public class UserCreateCompleteDAO {

	//ユーザー追加
	public void createUser(String loginUserId, String loginUserPassword, String userName)
			throws	SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		DateUtil dateUtil = new DateUtil();

		String sql = "INSERT INTO login_user_transaction (login_id,login_pass,user_name, insert_date)VALUES(?,?,?,?)";

		try {
			/*
			 * インターフェースPreparedStatementを変数preparedStatementとし、
			 * connectionつまりインターフェースConnectionのprepareStatementメソッドを、引数を変数sqlとして準備
			 */
			PreparedStatement ps = con.prepareStatement(sql);
			/*
			 * createUserメソッドの引数として渡された値つまり入力された内容をsql文の?に順次代入。
			 * 4つ目だけは、dateUtilつまりDateUtilクラスのgetDateメソッドを呼び出し、取得した値を代入。
			 */
			ps.setString(1, loginUserId);
			ps.setString(2, loginUserPassword);
			ps.setString(3, userName);
			ps.setString(4, dateUtil.getDate());
			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			con.close();
		}
	}
}
