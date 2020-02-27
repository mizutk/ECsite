<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/style.css">

<title>login画面</title>

</head>
<body>
	<div id="header"></div>
	<div id="main">
		<div id="top">
			<p>Login</p>
		</div>
		<div>
			<h3>商品を購入する際にはログインをお願いします。</h3>
			<s:form action="LoginAction">
			<!-- "loginUserId"と同名のactionクラスのフィールドに情報を渡す ※ブラウザでは<input type="text" name="loginUserId">と認識される-->
				<s:textfield name="loginUserId" />
				<!-- "password"と同名のactionクラスのフィールドに情報を渡す ※ブラウザでは<input type="password" name="loginPassword">と認識される-->
				<s:password name="loginPassword" />
				<!-- ブラウザでは<input type="submit" value="ログイン">と認識される -->
				<s:submit value="ログイン" />
			</s:form>
			<br />
			<div id="text-link">
				<p>
					新規ユーザー登録は <a href='<s:url action="UserCreateAction" />'>こちら</a>
				</p>
				<p>
					Homeへ戻る場合は <a href='<s:url action="GoHomeAction" />'>こちら</a>
				</p>
			</div>
		</div>
	</div>

	<div id="footer"></div>
</body>
</html>