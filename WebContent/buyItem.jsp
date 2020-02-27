<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="imagetoolbar" content="no" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<title>BuyItem画面</title>
		<style type="text/css">
			/* ========TAG LAYOUT======== */
			body {
				margin:0;
				padding:0;
				line-height:1.6;
				letter-spacing:1px;
				font-family:Verdana, Helvetica, sans-serif;
				font-size:12px;
				color:#333;
				background:#fff;
			}
			table {
				text-align:center;
				margin:0 auto;
			}

			/* ========ID LAYOUT======== */
			#top {
				width:780px;
				margin:30px auto;
				border:1px solid #333;
			}
			#header {
				width: 100%;
				height: 80px;
				background-color: black;
			}
			#main {
				width: 100%;
				height: 500px;
				text-align: center;
			}
			#footer {
				width: 100%;
				height: 80px;
				background-color: black;
				clear:both;
			}
		</style>
</head>
<body>
	<div id="header"></div>
	<div id="main">
		<div id="top">
			<p>BuyItem</p>
		</div>
					<s:if test="errorMassage != ''">
				<s:property value="errorMassage" escape="false" />
			</s:if>
		<div>
			<s:form action="BuyItemAction">
				<table>
					<tr>
						<td><span>商品名</span></td>
						<!-- LoginActionでMap型変数sessionに格納した値のうち、keyがbuyItem_nameに指定されてるvalueを返す -->
						<td><s:property value="session.buyItem_name" /></td>
					<tr>
						<td><span>値段</span></td>
						<!-- LoginActionでMap型変数sessionに格納した値のうち、keyがbuyItem_priceに指定されてるvalueを返す -->
						<td><s:property value="session.buyItem_price" /> <span>円</span>
						</td>
					</tr>
					<tr>
						<td><span>購入個数</span></td>
						<!-- select要素→countという識別子のプルダウンメニューを作成 -->
						<td><select name="count">
								<!-- option要素→select要素内に配置。メニューの選択肢を作成 -->
								<!-- selected属性→この属性を指定した選択肢は、最初から選択された状態になる -->
								<!-- value属性→送信される値を指定。optionタグで挟んだ、選択肢を選んだ際に送信される値。つまり、valueで指定した値によって、購入する個数が変わるということ -->
								<option value="1" selected="selected">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
						</select></td>
					</tr>
					<tr>
						<td><span>支払い方法</span></td>
						<!-- input要素→フォームの各種部品(入力欄・選択肢・ボタン)を作成 -->
						<!-- type属性→部品のタイプを指定。radioはラジオボタン -->
						<!-- name属性→フォーム部品を識別するための名前を指定 -->
						<!-- value属性→格納される値を指定 -->
						<!-- checked属性→最初から選択された状態を指定する -->
						<td><input type="radio" name="pay" value="1"
							checked="checked">現金支払い
							<input type="radio" name="pay"
							value="2">クレジットカード</td>
					</tr>
					<tr>
						<td>
							<!-- 購入という文字列が表示されたボタンを作成 --> <!-- 押下すると、BuyItemActionというactionを探しに、struts.xmlを見に行く -->
							<!-- その際に、value stackに、countとpayで選択された値を格納する -->
							<s:submit value="購入" />
						</td>
					</tr>
				</table>
			</s:form>

			<div>
				<!-- a要素→リンク先を指定した文字列(=アンカー)を作成するための要素。aタグ(アンカータグ)で囲む -->
				<!-- href属性→リンク先指定 -->
				<!-- 「こちら」という文字列をアンカーとし、それぞれのリンク先としてactionを設定。
                	アンカーの文字列を押下すると、それぞれのactionを探しに、struts.xmlを見に行く -->
				<p>
					前画面に戻る場合は<a href='<s:url action="GoHomeAction" />'>こちら</a>
				</p>
				<p>
					マイページは<a href='<s:url action="MyPageAction" />'>こちら</a>
				</p>
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>