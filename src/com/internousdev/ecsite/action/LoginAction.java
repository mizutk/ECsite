package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemDAO;
import com.internousdev.ecsite.dao.LoginDAO;
import com.internousdev.ecsite.dto.BuyItemDTO;
import com.internousdev.ecsite.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ログイン認証処理
 * Login.jspからログインID、ログインパスワードを受け取り
 * DBへ問い合わせを行います。
 */
public class LoginAction extends ActionSupport implements SessionAware {
	//フィールド変数JSPから受け取る値、ここではloginUserId,loginPassword,result,sessionを継承します。
	//※必ずJSPでの定義と同じ名前にします。
	private String loginUserId;
	private String loginPassword;
	private Map<String, Object> session;
	private LoginDAO loginDAO = new LoginDAO();
	private LoginDTO loginDTO = new LoginDTO();
	private BuyItemDAO buyItemDAO = new BuyItemDAO();

	//button@jsp → struts.xml → execute@Action
	public String execute() {
		String result = ERROR;
		//loginDAO.getLoginUserInfoでデータベースにアクセスして、結果をdtoに代入(login.jspから送られたloginUserId、loginPasswordが引数)
		/*
		 * 変数loginDTOに、loginDAO、つまりLoginDAOのgetLoginUserInfoメソッドを、先ほどvalue stackからsetされた
		 * loginUserId、loginPasswordの値を引数とし、実行してreturnされた値を代入。
		 */
		loginDTO = loginDAO.getLoginUserInfo(loginUserId, loginPassword);
		/*
		 * Map型変数sessionに、keyをloginUserとし、そのvalueを、上記の結果が格納されたloginDTOとする
		 */
		session.put("loginUser", loginDTO);

		//ユーザーが入力したloginUserが、DTOからもらってきた値に一致するかを確認します。
		//入力値からユーザー情報の検索を行います。
		//ログイン認証が成功した場合、次の画面で「商品情報」が必要なため商品情報を取得します。
		/*
		 * Map型変数sessionから、keyがloginUserで、さらにその中の変数LoginFlgを取得。
		 * しかしこのままでは、Object型なので(sessionを宣言する際にそう指定しているので)、
		 * (LoginDTO)で、getした値を代入し、boolean型にする。trueが返される
		 */
		/*
		 * このif文の条件式の内容は、Map型変数sessionからkeyとしてloginUserを指定し、さらにそこからgetLoginFlgで
		 * 格納されてる値を取り出す。これだけで、trueの値は取れるが、Map型変数に格納した時点で、getLoginFlgの値の型が
		 * Object型になってしまっている。それを元々のboolean型に戻す為に、(LoginDTO)という記載があり、
		 * getLoginFlgの値をLoginDTOに格納した状態の結果で処理する
		 */
		// ログイン情報を比較
		if(((LoginDTO) session.get("loginUser")).getLoginFlg()) {
			if((((LoginDTO) session.get("loginUser")).getAdminFlg() != null)
					&& (((LoginDTO) session.get("loginUser")).getAdminFlg().equals("1"))) {
				result = "admin";
			} else {

			result = SUCCESS;
			/*
			 *BuyItemDTOクラスを変数buyItemDTOとし、それに、buyItemDAOつまりBuyItemDAOクラスの
			 *getBuyItemInfoメソッドを実行した結果を代入
			 */
			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();
			/*
			 * Map型変数sessionに、loginDTOとbuyItemDTOに格納された値からそれぞれgetし、それらをkeyと対応付けて
			 * 格納する
			 */
			session.put("login_user_id", loginDTO.getLoginId());
			session.put("id", buyItemDTO.getId());
			session.put("buyItem_name", buyItemDTO.getItemName());
			session.put("buyItem_price", buyItemDTO.getItemPrice());
			session.put("item_stock", buyItemDTO.getItemStock());
			}
		}
			return result;
		}
		//execute（）メソッドの結果 SUCCESS、ERRORを返す
		//(それにより、あらかじめstruts.xmlに遷移先として定義したそれぞれのJSPに振り分けられる)
	/*
	 * struts.xmlから移動してきた際に、login.jspからvalue stackに格納された、loginUserIdとloginPasswordの値を
	 * value stackが、このクラスにsetする。そうすることで、最初に宣言してる変数も同じ値になる
	 * (と考えると、このクラスから見れば、value stackからgetしてると言える)
	 */
	/*
	 * login.jspからstruts.xmlを経由して、このLoginActionに来たら、まずは、
	 * value stackがこのクラスに、入力されたloginUserIdとloginPasswordの値をsetする
	 * そうすることで、一番最初に宣言していた同名の変数にも同じ値が格納されたことになる
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

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
