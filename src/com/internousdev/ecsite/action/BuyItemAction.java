package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BuyItemAction extends ActionSupport implements SessionAware{

	//jspファイルから受け取る値の定義　※jspでの定義と変数名を合わせる
	private Map<String, Object> session;
	private int count;
	private String pay;
	private String errorMessage;

	public String execute() {
		String result=SUCCESS;

		int itemStock = Integer.parseInt(session.get("item_stock").toString());
		if(count > itemStock) {
			setErrorMessage("在庫が足りません。");
			return ERROR;
		}

		//Map型変数sessionに、keyをcount、valueをcountとして格納
		session.put("count",count);
		//integer:整数
		//parseIntメソッド:文字列から数値へ変換
		//toString 数値を文字列へ
		//?何をしている場所か
		//object型：なんでも入るもの。
		//元の型に戻す必要がある。
		//object-string-int
		/*
		 * Integer(整数という意味)。parse(パース。分析、解析、変換という意味。)
		 * parseIntメソッド→Integerクラスに用意されてる。引数に指定さ文字列をint型の値として返す。
		 * いきなりObject型からは変更できなくて、一度、toStringメソッドでString型の値にしている
		 *parseIntメソッドの引数の中身→Map型変数sessionのkeyがcountとなっているvalueを返し、
		 *その値をtoStringメソッドでString型に変換。
		 *但しここで使われているtoStringメソッドは、Integerクラスのメソッドではなく、
		 *Objectクラスのメソッドになる
		 *Objectクラス→すべてのクラスのスーパークラス。何かひとつクラスを作っただけで、このクラスから
		 *継承したことになる。そのため、ありとあらゆるクラスは「Objectクラスのサブクラス」ということになる
		 *このクラスは既にActionSupportクラスを継承しているので、一緒にObjectクラスも継承しており、
		 *toStringコンストラクタを呼び出せるという寸法
		 */
		int intCount = Integer.parseInt(session.get("count").toString());
		int intPrice = Integer.parseInt(session.get("buyItem_price").toString());
		//buyItem_priceに商品数と値段の商を入れる
		//Map型変数sessionに、keyをtotal_price、valueをintCount x intPriceという計算式を格納
		session.put("total_price",intCount * intPrice);
		String payment;

		if(pay.equals("1")) {
			payment = "現金払い";
			session.put("pay",payment);
		}else {
			payment = "クレジットカード";
			session.put("pay",payment);
		}
		return result;
	}

	/*
	 * まずは、struts.xmlからこのクラスに来たら、value stackが、下記に用意した通りの変数の値を
	 * このクラスにsetする。下記のセッターは、あくまで、value stackから見た動作を表現してる。
	 * なので、value stackに格納されていた、buyItem.jspページで選択したcountとpayの値が
	 * このクラスの変数にも格納される
	 */

	public void setCount(int count) {
		this.count = count;
	}

	public void setPay(String pay) {
		this.pay= pay;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	//Override:処理を変更。親クラスのメソッドを子クラスで再定義
	@Override
	public void setSession(Map<String, Object> session){
		this.session = session;
	}

	public Map<String,Object> getSession(){
		return this.session;
	}

}
