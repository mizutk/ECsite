package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.UserCreateCompleteDAO;
import com.opensymphony.xwork2.ActionSupport;

public class UserCreateCompleteAction extends ActionSupport implements SessionAware{
		private String loginUserId;
		private String loginPassword;
		private String userName;
		private Map<String, Object> session;
		private UserCreateCompleteDAO userCreateCompleteDAO = new UserCreateCompleteDAO();

		public String execute() throws SQLException{
				//DAOを経由して、入力された内容をDBに登録
				userCreateCompleteDAO.createUser(session.get("loginUserId").toString(),
						/*
						 * userCreateCompleteDAOつまりUserCreateCompleteDAOの、createUserメソッドを、
						 * Map型変数sessionの、それぞれのkeyに対応するvalueをgetし、その値をString型に変更して、
						 * それらの値を引数として実行
						 */
						session.get("loginPassword").toString(),
						session.get("userName").toString());
						String result = SUCCESS;
						return result;
			}

		/*
		 * userCreateConfirm.jspからstruts.xmlを経由して移動してきた際に、value stackに格納されてる
		 * loginUserId、loginPassword、userName、sessionの値を、value stackがこのクラスにsetする
		 * get、setはvalue stackから見た動作を表してる。
		 * get→value stackがこのクラスから対象の変数をgetする
		 * set→value stackからこのクラスに、対象の変数をsetする
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
}