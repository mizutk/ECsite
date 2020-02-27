package com.internousdev.ecsite.action;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.UserCreateConfirmDAO;
import com.opensymphony.xwork2.ActionSupport;

public class UserCreateConfirmAction extends ActionSupport implements SessionAware{
	private String loginUserId;
	private String loginPassword;
	private String userName;
	private Map<String, Object> session;
	private String errorMessage;

	/*
	 * value stackから取得した値が、このクラス内の変数にも格納される
	 */
	public String execute() {
		String result = SUCCESS;
		//未入力項目の有無を確認
		/*
		 * 下のif文→equalsの対象が空なので、3つの変数が空であるかを尋ねているが、
		 * それぞれの文の最初に、否定を意味する!があることから、空ではないつまり、何か値が入っているかを尋ねており、
		 * さらに、&&で3つの変数全てにおいて、何か値が入っている状態であるかを尋ねている。
		 * そして、trueの場合、Map型変数sessionに、それぞれの変数をkeyとセットで格納する
		 */

		if(!(loginUserId.equals(""))
				&& !(loginPassword.equals(""))
				&& !(userName.equals(""))) {
			UserCreateConfirmDAO userCreateConfirmDAO = new UserCreateConfirmDAO();
			if(!userCreateConfirmDAO.isExistUser(loginUserId)) {

			//次の確認画面で表示したい値をセッションに格納
					session.put("loginUserId", loginUserId);
					session.put("loginPassword", loginPassword);
					session.put("userName", userName);
			}else {
				setErrorMessage("すでに登録されているログインIDです。");
				result = ERROR;
			}
					/*
					 * 下記のif文が、falseの場合つまり、1つでも値が空の場合、このページ下部に記載されてるsetErrorMessageメソッドに
					 * 「未入力の項目があります」という文字列が格納され、変数errorMessageの値となる
					 */
		} else {
			setErrorMessage("未入力の項目があります。");
			result = ERROR;
		}
		return result;
	}

	/*
	 * まずは、userCreate.jspページで、入力した内容をvalue stackからgetする
	 * (loginUserId、loginPassword、userName)
	 */

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Map<String,Object> getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String,Object> session) {
		this.session = session;
	}

	public String getErrorMessage(){
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}