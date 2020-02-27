<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<title>BuyItemConfirme画面</title>

<script type="text/javascript">
	//function→関数 submitAction→関数名 url→引数をこのurlに代入
	function submitAction(url) {
		//$('form')→対象の要素をformに指定。
		//attr→アトリビュート(属性)の略。attrメソッド→HTML要素の属性を取得できたり。
		//この場合だと、action属性を指定し、さらに対象として、submitActionの引数であるurlを第二引数としてる。
		//これによって、引数urlに格納されているものを、action属性(データの送信先を設定するための属性)つまり、データの送信先として指定
		$('form').attr('action', url);
		//submitメソッドで、データを指定のactionへ送信
		$('form').submit();
	}
</script>

</head>
<body>
	<div id="header"></div>
	<div id="main">
		<div id="top">
			<p>BuyItem</p>
		</div>
		<div>
			<s:form>
				<tr>
					<td>商品名</td>
					<td><s:property value="session.buyItem_name" /></td>
				<tr>
					<td>値段</td>
					<td><s:property value="session.total_price" /> <span>円</span>
					</td>
				</tr>
				<tr>
					<td>購入個数</td>
					<td><s:property value="session.count" /> <span>個</span></td>
				</tr>
				<tr>
					<td>支払方法</td>
					<td><s:property value="session.pay" /></td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr>
					<!-- onclick属性→該当要素をクリックした際に、起動するスクリプトを指定する
                    　　　　　　　　　今回の場合は、submitActionというスクリプトを、それぞれ引数を指定した上で、実行。 -->
					<td><input type="button" value="戻る"
						onclick="submitAction('HomeAction')" /></td>

					<td><input type="button" value="完了"
						onclick="submitAction('BuyItemConfirmAction')" /></td>
				</tr>
			</s:form>
		</div>
		<div>
			<p>
				前画面に戻る場合は<a href='<s:url action="GoHomeAction" />'>こちら</a>
			</p>
			<p>
				マイページは<a href='<s:url action="MyPageAction" />'>こちら</a>
			</p>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>