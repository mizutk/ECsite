//packageの宣言
package com.internousdev.ecsite.util;

//データベースとの接続をするためのパッケージ
import java.sql.Connection;
//JDBCドライバを管理するためのクラス
import java.sql.DriverManager;
//データベースアクセスエラー等の情報を提供するための例外
import java.sql.SQLException;

public class DBConnector {

	/*MySQL接続に必要な情報を設定します。*/
	/* JDBCドライバー名 */
	//driverNameにJDBCドライバーを代入
	private static String driverName = "com.mysql.jdbc.Driver";
	/* DB接続URL */
	//url指定 ?以降はオプション lodalhost=サーバー名　testdb=データベース名
	private static String url ="jdbc:mysql://localhost/ecsite";
	/* DB接続ユーザー名 */
	//mysqlにログインするための準備
	private static String user = "root";
	/* DB接続パスワード */
	//mysql　rootアカウントのパスワードを"root"に指定
	private static String password ="mysql";

	//DBに接続をするためのクラス(下に出てくるAPIのgetConnectionとは別物)
	public Connection getConnection() {
		//Connection型のconの初期化
		Connection con = null;

	//try〜catchは例外処理構文
	//try:例外が発生する可能性のある処理
	try {
		//ドライバーをロードして使う準備
		Class.forName(driverName);
		/* 接続情報から自分のパソコンにインストールされているMySQLへ接続する準備が整います。 */
		// 指定されたデータベースのURLへの接続を試みるメソッド(上で代入したurl、user、passwordも代入)
		//指定したデータベースへの接続　結果をconへ代入
		con = (Connection) DriverManager.getConnection(url,user,password);
		//catch:例外処理。処理中にSQL関連のエラーが発生した際に実行する処理です。(例外が発生しなければ行われない処理)
		//Exception→例外の型 e→引数
		//try内でエラーが発生した場合、catchが受け取り、printStachTrace()でエラーに至る履歴の表示をする
	}catch(ClassNotFoundException e) {
		//スタックトレースを出力
		//StackTrace:実行したメソッドの時系列の一覧
		//stack=積み重ね、trace=跡
		//ClassNotFoundException クラスが見つからないエラーの場合、スタックトレースを出力
		e.printStackTrace();
		//catch:例外処理。処理中にSQL関連のエラーが発生した際に実行する処理です。(例外が発生しなければ行われない処理)
		//Exception→例外の型 e→引数
	}catch(SQLException e) {
		//SQLException データベース処理に関するエラーの場合、スタックトレースを出力
		e.printStackTrace();
	}
	/* MySQLに接続できたか情報を渡します。 */
	//DBに接続した結果をconで返す
	return con;
	}
}
