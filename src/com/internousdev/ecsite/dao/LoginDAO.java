package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.ecsite.dto.LoginDTO;
import com.internousdev.ecsite.util.DBConnector;

public class LoginDAO {
	/**
	 * ログインユーザ情報取得メソッド
	 */
	//LoginDTO型を最後に呼び出し元に渡すので、LoginDTOを戻り値にしたメソッドを作ります。Actionクラスの値を引数として受け取ります。
	//メソッド名は処理内容を分かりやすくするために「getLoginUserInfo」にします。
	//Login.jsp→LoginActionから渡された引数name、passwordを扱うselectメソッド
	/*
	 * LoginActionのexecuteメソッド内で、入力した内容を引数として、呼ばれる
	 */
	public LoginDTO getLoginUserInfo(String loginUserId, String loginPassword) {
		//---2.DBConnectorのインスタンス化---
		//データベースに接続するためのフィールドとメソッドを持ったインスタンスの生成
		DBConnector db = new DBConnector();
		//3.getConnectionの呼び出し（DBと接続する）
		//データベースへの接続を実際に行うクラスをconに代入
		Connection con = db.getConnection();
		//---LoginDTOのインスタンス化----
		//データベースへアクセスした結果を格納するLoginDTOインスタンスを生成
		LoginDTO loginDTO = new LoginDTO();
		//4.sql文を書く：値は?を入れておく　（どんな値でも使いまわしできるようにするため）
		//データを取得するsql文の作成
		//Stringの変数sqlとして、sql文を用意。意味は、login_user_transactionテーブル内で、入力されたlogin_idとlogin_passのセットはあるかどうか
		String sql = "SELECT * FROM login_user_transaction where login_id = ? AND login_pass = ?";

		//try:例外が発生する可能性のある処理
		try {
			//5.PreparedStaetment (DBまで運んでくれる箱のイメージ)に代入
			//セキュリティを考慮し、javaでは、PreparedStatementを利用します。
			//PreparedStatement:プリコンパイルされたSQL文を表すオブジェクトです。
			//SQL文は、プリコンパイルされ、PreparedStatementオブジェクトに格納されます。
			//その後、このオブジェクトは、この文を複数回効率的に実行するために使用できます。
			//sql文を表すPreparedStatement型のpsに、上記sql文の代入
			/*
			 * PreparedStatementクラスを変数preparedStatementとし、その中に、connectionつまりConnectionクラスのprepareStatementメソッドで
			 * 引数として変数sqlを指定したものを代入しておく
			 */
			PreparedStatement ps = con.prepareStatement(sql);
			//定義したSQL文の1番目の?パラメーターにActionかた送られたnameが入る
			//sql文の1つ目の？(プレースホルダー)にloginUserIdをセット
			ps.setString(1, loginUserId);
			//定義したSQL文の2番目の?パラメーターにActionからおくられたpasswordが入る
			//sql文の2つ目の？(プレースホルダー)にloginPasswordをセット
			ps.setString(2, loginPassword);

			//select文のSQL文を実行するメソッドで、戻り値はResultSetになります。
			//sql文の実行(結果はResultSet型のrsに入る)
			/*
			 * ResultSetクラスを変数resultSetとし、preparedStatementつまりPreparedStatementの、executeQueryメソッドを実行し
			 * preparedStatementに代入されたString型変数sqlをsql文として実行した結果を代入
			 */
			ResultSet rs = ps.executeQuery();
			//実行結果の1行次、つまりカラム名の次に実データが入っている場合、
			/*
			 * resultSetつまりResultSetのnextメソッドを使い、変数resultSetに何かしら値がtableのように格納され、
			 * 次の行を参照できるようであればtrueを返す
			 */
			if (rs.next()) {
				//6.sql文の？に入れる値をsetする
				//select文でDBから取得した情報をString型に変換してDTOクラスに格納します。
				//LoginDTOクラスのsetLoginId,setLoginPassword,setUsernameを利用します。
				/*
				 * resultSetに格納された値のうち、それぞれの変数に該当する値をgetし、
				 * loginDTOつまりLoginDTOクラスの各setメソッドに格納していく
				 */
				loginDTO.setLoginId(rs.getString("login_id"));
				loginDTO.setLoginPassword(rs.getString("login_pass"));
				loginDTO.setUserName(rs.getString("user_name"));
				//login_idの値がNULLかどうかを判定する
				/*
				 * resultSetに格納された値のうち、login_idがnull(空)でない(equalsとそれを!で囲んでる)場合
				 * loginDTOのsetLoginFlgにtrueを格納する
				 */
				if (rs.getString("login_id") != null) {
					loginDTO.setLoginFlg(true);
				}
				loginDTO.setAdminFlg(rs.getString("admin_flg"));
			}

			//catch:例外処理。処理中にSQL関連のエラーが発生した際に実行する処理です。(例外が発生しなければ行われない処理)
			//Exception→例外の型 e→引数
		} catch (Exception e) {
			//スタックトレースを出力
			//StackTrace:実行したメソッドの時系列の一覧
			//stack=積み重ね、trace=跡
			e.printStackTrace();
		}
		//loginDTOに入った値を、呼び出し元であるActionクラスに渡す。
		//nextメソッドがtrueだった場合に値が格納された、loginDTOがreturnされる
		return loginDTO;
	}
}
