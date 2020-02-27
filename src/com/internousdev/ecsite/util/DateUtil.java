//packageの宣言
package com.internousdev.ecsite.util;

//日付書式を扱うクラス
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 現在時刻取得メソッド
	 *
	 */
	public String getDate() {
		//Dateオブジェクトを生成
		//---dateのインスタンス化---
		//Dateクラスを変数dateとしてインスタンス化(その際に、現在の時刻が取得が出来る)
		Date date = new Date();
		//フォーマットパターンを指定して、SimpleDateFormatオブジェクトsimpleDateFormatを生成
		//y:年,M:月,d:週,H:時,m:分,s:秒
		//---simpleDateFormatのインスタンス化---
		/*
		 * SimpleDateFormatクラスを変数simpleDateFormatとしてインスタンス化(その際に、
		 * 呼び出すコンストラクタの型を指定)
		 */
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	 //指定書式に変換して戻す
	/*
	 * simpleDateFormatつまりSimpleDateFormatのformatメソッドを呼び出す
	 * dateつまり現在の時刻を、SimpleDateFormatをインスタンス化した時に指定した
	 * 型に合わせた形の値を返す
	 */
	return simpleDateFormat.format(date);
}
}
