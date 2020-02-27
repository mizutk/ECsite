<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/style.css">

<title>MyPage画面</title>

</head>
<body>
	<div id="header"></div>
	<div id="main">
		<div id="top">
			<p>MyPage</p>
		</div>
		<div>
			<!-- MyPageActionのexecuteメソッドの中で、宣言した、List型変数のmyPageListが空の場合 -->
			<s:if test="myPageList==null">
				<h3>ご購入情報はありません。</h3>
			</s:if>

			<!-- 変数messageが空の場合。Actionクラスからstruts.xmlを経由してmyPage.jspへ来る際に
            ActionクラスのgetMessageでvalue stackは変数messageの値を取得しているので判断できる -->
			<s:elseif test="message==null">
				<h3>ご購入情報は以下になります。</h3>
				<table border="1">
					<tr>
						<th>商品名</th>
						<th>値段</th>
						<th>購入個数</th>
						<th>支払方法</th>
						<th>購入日</th>
					</tr>

					<!-- iterator要素→コレクションや配列から、要素を一つずつ取り出し、それらを値がある分だけ
                    その処理を繰り返す。対象の配列にmyPageListを指定 -->
					<s:iterator value="myPageList">
						<tr>
							<td><s:property value="itemName" /></td>
							<td><s:property value="totalPrice" /></td>
							<td><s:property value="totalCount" /></td>
							<td><s:property value="payment" /></td>
							<td><s:property value="insert_date" /></td>
						</tr>
					</s:iterator>
				</table>

				<s:form action="MyPageAction">
					<!-- inputタグのtype属性でhiddenを指定→ブラウザ上に表示されない非表示データを送信することが出来る
                    formでデータが送信される際、name属性で指定した名前と、value属性で指定した値が一組となって送信される -->
					<input type="hidden" name="deleteFlg" value="1">
					<s:submit value="削除" />
				</s:form>
			</s:elseif>

			<s:if test="message !=null">
				<h3>
					<s:property value="message" />
				</h3>
			</s:if>
			<div id="text-right">
				<p>
					Homeへ戻る場合は <a href='<s:url action="GoHomeAction" />'>こちら</a>
				</p>
				<p>
					ログアウトする場合は <a href='<s:url action="LogoutAction" />'>こちら</a>
				</p>
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>