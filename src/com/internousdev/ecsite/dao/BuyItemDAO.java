package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.ecsite.dto.BuyItemDTO;
import com.internousdev.ecsite.util.DBConnector;

public class BuyItemDAO {
	public BuyItemDTO getBuyItemInfo() {
		DBConnector db = new DBConnector(); //DB接続用にフィールドとメソッドを持ったインスタンス作成
		Connection con = db.getConnection(); //実際にDB接続するクラスをconに代入
		BuyItemDTO buyItemDTO = new BuyItemDTO(); //DTO:空箱

		/*
		 * String型変数sqlにsql文を文字列として準備
		 * 意味は、item_info_transactionテーブルのうち、id,item_name,item_priceのカラムだけ表示してくれってこと
		 */
		String sql = "SELECT id, item_name, item_price, item_stock FROM item_info_transaction";

		//try:例外が発生する可能性のある処理
		try {
			/*
			 * PreparedStatementクラスを変数preparedStatementとし、connectionつまりはConnectionクラスの
			 * prepareStatementメソッドに引数として変数sqlをあてがい、準備したものを代入しておく
			 */
			PreparedStatement ps = con.prepareStatement(sql);
			//select文のSQL文を実行するメソッドで、戻り値はResultSetになります。
			//ResultSet:データベースから取得したデータ群（結果セット）
			//executeQueryメソッドの戻り値：ResultSet
			/*
			 * ResultSetクラスを変数resultSetとし、preparedStatementつまり、prepareStatement(sql)を代入された変数を
			 * PreparedStatementクラスのexecuteQueryメソッドで、sql文として実行し、その結果を代入
			 */
			//データベースへSQL文の実行
			//rs（ResultSet）へSQL実行結果の格納
			ResultSet rs = ps.executeQuery();

			//next():カーソルは次の行に移動（初期状態では、カーソルは最初の行の先頭）
			/*
			 * executeQueryメソッドの実行結果を代入してあるresultSetに、ResultSetのnextメソッドを使うことで
			 * そもそも値が格納されているのかどうかを調べられる。値が格納されていれば(次の行があれば)、trueを返す
			 */
			if (rs.next()) {
				//select文でDBから取得した情報をint,String型に変換してDTOクラスに格納します。
				//buyItemDTOクラスのsetId,setItemName,setItemPriceを利用します。
				/*
				 * resultSetに格納されてる値から、引数に指定された値をgetし、その値を、buyItemDTOつまりBuyItemDTOの
				 * 各setメソッドに格納する
				 */
				//rs(result set)の"id"カラムに該当するint型の値を持って来て(get)、buyItemDTOという空の箱にセット(set)する。
				buyItemDTO.setId(rs.getInt("id"));
				buyItemDTO.setItemName(rs.getString("item_name"));
				buyItemDTO.setItemPrice(rs.getString("item_price"));
				buyItemDTO.setItemStock(rs.getInt("item_stock"));
			}

		//処理中にSQL関連のエラーが発生した際に実行する処理です。
		} catch (Exception e) {
			//スタックトレースを出力
			//StackTrace:実行したメソッドの時系列の一覧
			//stack=積み重ね、trace=跡
			e.printStackTrace();
		}
		//buyItemDTOに入った値を、呼び出し元であるActionクラスに渡す。
		/*
		 * resultSetに格納された値から、それぞれ値をgetし、値をsetされた変数buyItemDTOを呼び出し元つまり
		 * LoginActionに返す
		 */
		return buyItemDTO;
	}
}

