//Action:ユーザーにより画面から送られてきたリクエストを取得する
//		　処理内容に応じてDAOやDTOクラスを呼び出し、最終的に次のJSPへ値を返す

//packageの宣言
package com.internousdev.ecsite.action;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class GoHomeAction extends ActionSupport implements SessionAware{
	//jspファイルから受け取る値の定義　※jspでの定義と変数名を合わせる
	private Map<String,Object> session;

	public String execute() {
		//戻り値にSUCCESSを代入する　SUCCESSはActionSupportが実装しているActionインターフェースに定義されている定数SUCCESS=“success”
		//struts.xmlで次に遷移するページを判断する。
		//executeメソッドを呼ばれたら、successを返すだけ
		return SUCCESS;
	}
	public Map<String, Object> getSession(){
		return this.session;
	}

	@Override
	public void setSession(Map<String,Object>session) {
		this.session = session;
	}
}
