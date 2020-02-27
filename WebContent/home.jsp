<!-- ページの設定 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- struts2のカスタムタグ(s:form等)を使えるようにしている -->
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/style.css">

<title>Home画面</title>

</head>
<body>
	<div id="header">
	</div>
	<div id="main">
		<div id="top">
			<p>Home</p>
		</div>
		<div>
			<s:form action="HomeAction">
				<!-- 商品購入という文字列を表示させたボタンを設置。押すと、HomeActionというactionを探しに、
            struts.xmlを見に行く -->
				<s:submit value="商品購入" />
			</s:form>

			<!-- Map型変数sessionの、keyがidに設定されてるvalueがnullで無い場合、つまり何かしら値が入っていた場合
        下記2行の処理が実行される -->
			<s:if test="#session.login_user_id !=null">
			<!-- a要素→リンクが設定された文字列(=アンカー)を作成するための要素で、aタグ(アンカータグ)で囲む -->
                <!-- href属性→リンク先指定 -->
                <!-- リンク先として、LogoutActionを設定された、「こちら」という文字列を配置
                「こちら」という文字列が押下されたら、LogoutActionを探しに、struts.xmlを見に行く -->
			<p>ログアウトする場合は
			<a href='<s:url action="LogoutAction"/>'>こちら</a></p>
			</s:if>

		</div>
	</div>
	<div id="footer">
	</div>
</body>
</html>