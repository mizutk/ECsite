package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemDAO;
import com.internousdev.ecsite.dto.BuyItemDTO;
import com.opensymphony.xwork2.ActionSupport;


public class HomeAction extends ActionSupport implements SessionAware{
	//session取得メソッドを実装
	//jspファイルから受け取る値の定義　※jspでの定義と変数名を合わせる
	private Map<String, Object>session;

	//ActionSupportクラスのexecuteメソッドをオーバーライド(上書き)
	/*
	 * home.jspでログインボタンを押下した際に、struts.xmlで指定された、このexecuteメソッドが呼び出される
	 */

	/**
	 * ログインボタン押下時に実行
	 * ログイン画面へ遷移します。
	 */
	public String execute() {
		String result ="login";
		//ログイン済み判定を行います。
		//一度ログインしている場合はログイン認証画面に遷移させることなく、商品画面へ遷移させます。
		//containsKey:指定したキーが存在するか確認を行い、キーが存在する場合はtrueを返します。
		//Map.containsKey(検索するキー)
		//もし、sessionに"login_user_id"がある場合、trueを返す。
		/*
		 * Mapクラスに含まれるcontainsKeyメソッド→引数に指定したkeyが存在するか否か
		 * つまり、上記なら、Map型変数sessionの中に、keyとしてidがあるかどうか
		 * 存在する→trueを返す
		 */
		if(session.containsKey("login_user_id")) {
			// アイテム情報を取得
			//BuyItemDAOクラスをbuyItemDAOという名前でインスタンス化(このクラス内にコピー)
			BuyItemDAO buyItemDAO = new BuyItemDAO();
			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();
			//DBから取得した商品情報をセッションに格納しています。
			//"id"というsession keyに、"buyItemDTO.getId()"をset
			session.put("id", buyItemDTO.getId());
			//"buyItem_name"というsession keyに、"buyItemDTO.getItemName()"をset
			session.put("buyItem_name", buyItemDTO.getItemName());
			//"buyItem_price"というsession keyに、"buyItemDTO.getItemPrice()"をset
			session.put("buyItem_price", buyItemDTO.getItemPrice());
			//excecuteメソッドの戻り値として、ログイン状態の場合は「SUCCESS」
			//戻り値にSUCCESSを代入する　SUCCESSはActionSupportが実装しているActionインターフェースに定義されている定数SUCCESS=“success”
			result = SUCCESS;
	}
		//ログインしてない場合は"login"を返す
		/*
		 * 上記のif文が走らなければresultはlogin、走ればsuccessがreturnされる
		 */
		return result;
	}
//Mapのgetメソッドでkeyから値を取り出します。
public Map<String,Object>getSession(){
	return this.session;
}
//定義し直す
@Override
public void setSession(Map<String, Object> session) {
	this.session = session;
	}
}