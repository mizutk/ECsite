package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.MyPageDAO;
import com.internousdev.ecsite.dto.MyPageDTO;
import com.opensymphony.xwork2.ActionSupport;

public class MyPageAction extends ActionSupport implements SessionAware{
	private Map<String,Object>session;
	private MyPageDAO myPageDAO = new MyPageDAO();
	private ArrayList<MyPageDTO> myPageList = new ArrayList<MyPageDTO>();
	private String deleteFlg;
	private String message;

	/**
	 * 商品履歴取得メソッド
	 */
	public String execute() throws SQLException{

		if(!session.containsKey("login_user_id")) { //login_user_id無＝true
			return ERROR;
		}
		/*
		 * 商品購入直後に呼んだときは、deleteFlgはそもそも出てきていないので、
		 * 空(=null)。なので、下記の処理が走る
		 */
		if(deleteFlg == null) {
			//履歴削除がされているかチェック
			/*
			 * それぞれString型の変数に、Map型変数sessionに格納されてるkeyに対応するvalueを、
			 * toStringメソッドでString型の値にして、代入
			 */
			String item_transaction_id = session.get("id").toString();
			String user_master_id = session.get("login_user_id").toString();

			//DBから取得した履歴情報を、[myPageList]に格納
			/*
			 * 上記の2文で値を代入した変数を引数とし、myPageDAOつまりMyPageDAOクラスのgetMyPageUserInfoメソッドを
			 * 実行し、その結果を変数myPageListに代入する
			 * つまり、DAOクラスのsql文を実行して、値を格納された、List型変数のmyPageDTOを代入
			 */

			myPageList = myPageDAO.getMyPageUserInfo(item_transaction_id,user_master_id);
		} else if(deleteFlg.equals("1")) { //deleteメソッドを呼び出し、履歴削除
			delete();
		}

		String result = SUCCESS;
		return result;
		}

	/**
	 * 商品履歴削除
	 */
	public void delete() throws SQLException{
			String item_transaction_id = session.get("id").toString();
			String user_master_id = session.get("login_user_id").toString();
			int res = myPageDAO.buyItemHistoryDelete(item_transaction_id, user_master_id); //DBからの削除件数をresに格納

			if(res > 0) { //1件以上削除＝正しい
				myPageList = null;
				setMessage("商品情報を正しく削除しました。");
			}else if(res == 0) {
				setMessage("商品情報の削除に失敗しました。");
			}
		}


		public void setDeleteFlg(String deleteFlg) {
			this.deleteFlg = deleteFlg;
		}

		public ArrayList<MyPageDTO> getMyPageList(){
			return this.myPageList;
		}

		public String getMessage() {
			return this.message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Map<String,Object> getSession(){
			return this.session;
		}

		@Override
		public void setSession(Map<String,Object>session) {
			this.session = session;
			}
		}