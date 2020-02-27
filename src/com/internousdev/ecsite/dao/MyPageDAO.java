package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.ecsite.dto.MyPageDTO;
import com.internousdev.ecsite.util.DBConnector;

public class MyPageDAO {
	//MyPageDTO型を最後に呼び出し元に渡すので、MyPageDTOを戻り値にしたメソッドを作ります。Actionクラスの値を引数として受け取ります。
	//DBから購入履歴を所得するためのメソッドです。
	public ArrayList<MyPageDTO> getMyPageUserInfo(String item_transaction_id, String user_master_id)
			throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection(); //DB接続を実際に行うクラスをconに代入
		ArrayList<MyPageDTO> myPageDTO = new ArrayList<MyPageDTO>();

		/*
		 * sql文について。
		 * select文で表示するわけですが、left joinでuser_buy_item_transactionテーブルとitem_info_transactionテーブルの内容を
		 * 同じカラムについてはまとめた状態で合体させるというもの。left join ～ on で一つの公式の形
		 * where ～ and ～ で両方の条件に合致するデータはあるか。
		 * order by→データの昇降順を指定。
		 */
		String sql = "SELECT ubit.id, lit.item_name, ubit.total_price, ubit.total_count, ubit.pay, ubit.insert_date FROM user_buy_item_transaction ubit LEFT JOIN item_info_transaction lit ON ubit.item_transaction_id = lit.id WHERE ubit.item_transaction_id= ? AND ubit.user_master_id = ? ORDER BY insert_date DESC";
		//LEFT JOINでユーザー情報と履歴情報を一括取得
		try {
			/*
			 * PreparedStatementクラスを変数preparedStatementとし、connectionつまりConnectionクラスのprepareStatementメソッドを
			 * 変数sqlを引数としてあてがい、準備したものを代入
			 */
			PreparedStatement ps = con.prepareStatement(sql);
			/*
			 * preparedStatementつまりPreparedStatementクラスの、setStringメソッドを使い、
			 * 引数のsqlの内容の?に、順番に変数(item_transaction_id、user_master_id)をあてがう
			 */
			ps.setString(1, item_transaction_id);
			ps.setString(2, user_master_id);
			/*
			 * ResultSetクラスを変数resultSetとし、preparedStatementつまりPreparedStatementクラスの
			 * executeQueryメソッドを使用し、変数sqlに格納してる文字列をsql文として処理し、表の形で返した
			 * 値を代入
			 */
			ResultSet rs = ps.executeQuery();
			/*
			 * resultSetつまり、ResultSetクラスのnextメソッドを使用して、変数resultSetに格納されてる
			 * 表に値が格納されてるか(詳しく言えば、ポインタは1行目(項目行)にあって、値が格納されていれば、2行目つまり実際の値
			 * が存在するはずなので、それらがあるかどうかでtureとfalseを返す)を確認
			 */
			while (rs.next()) {
				MyPageDTO dto = new MyPageDTO();
				//取得した結果を一件ずつDTOに格納し、さらにDTOをArrayListに格納しています。
				/*
				 * sql文を実行した結果が格納されているresultSetから、getメソッドで、各項目(id、item_name等)に該当する
				 * 値を取得し、dtoつまりMyPageDTOのsetメソッドを使って、値をsetしていく。
				 */
				dto.setId(rs.getString("id"));
				dto.setItemName(rs.getString("item_name"));
				dto.setTotalPrice(rs.getString("total_price"));
				dto.setTotalCount(rs.getString("total_count"));
				dto.setPayment(rs.getString("pay"));
				dto.setInsert_date(rs.getString("insert_date"));
				/*
				 * 上記の処理を終えたら、addメソッドに変数dtoつまり上記の処理で値をsetされた
				 * MyPageDTOを引数として選択し、List型変数のmyPageDTOに加える
				 */
				myPageDTO.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		/*
		 * 呼び出し元に返す値は、sql文を実行した結果値を格納された
		 * List型変数のmyPageDTO。
		 */
		return myPageDTO;
	}

	/*
	 * myPage.jspで削除ボタンを押下し、MyPageActionに呼ばれる
	 */
	public int buyItemHistoryDelete
	(String item_transaction_id, String user_master_id) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "DELETE FROM user_buy_item_transaction WHERE item_transaction_id= ? AND user_master_id = ?";

		PreparedStatement ps;
		int result = 0;
		try {
			/*
			 * 変数resultに、用意した変数sqlをsql文として実行し、executeUpdateメソッドによって
			 * 返り値は、int型の値になる
			 */
			ps = con.prepareStatement(sql);
			ps.setString(1, item_transaction_id);
			ps.setString(2, user_master_id);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		//Actionクラスに削除した件数を返します。
		return result;
	}
}