package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

//struts2が提供するActionSupportクラスをインポート
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;

	public String execute() {
		session.clear();
		return SUCCESS;
	}

	/**
	 * Session情報
	 */
	public Map<String, Object> getSession() {
		return this.session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
