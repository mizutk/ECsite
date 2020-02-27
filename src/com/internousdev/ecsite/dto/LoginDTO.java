//LoginDAOが取得した値をLoginActionへ戻す際、値を格納する
package com.internousdev.ecsite.dto;

public class LoginDTO {
	private String loginId;
	private String loginPassword;
	private String userName;
	private boolean loginFlg = false;
	private String adminFlg;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
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

	//Actionクラスから呼び出され、LoginFlgフィールドの値をActionに渡します。
	public boolean getLoginFlg() {
		return loginFlg;
	}

	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のLoginFlgフィールドに格納します。
	public void setLoginFlg(boolean loginFlg) {
		this.loginFlg = loginFlg;
	}

	public String getAdminFlg() {
		return adminFlg;
	}

	public void setAdminFlg(String adminFlg) {
		this.adminFlg = adminFlg;
	}
}