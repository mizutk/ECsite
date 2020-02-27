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
<title>ユーザー一覧画面</title>
<style type="text/css">
/* ========TAG LAYOUT======== */
body {
	margin: 0;
	padding: 0;
	line-height: 1.6;
	letter-spacing: 1px;
	font-family: Verdana, Helvetica, sans-serif;
	font-size: 12px;
	color: #333;
	background: #fff;
}

table {
	text-align: center;
	margin: 0 auto;
}

/* ========ID LAYOUT======== */
#top {
	width: 780px;
	margin: 30px auto;
	border: 1px solid #333;
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
	clear: both;
}

#text-right {
	display: inline-block;
	text-align: right;
}
</style>

</head>
<body>
	<div id="header">
		<div id="pr"></div>
	</div>
	<div id="main">
		<div id="top">
			<p>MyPage</p>
		</div>

		<div>
			<s:if test="UserInfoDTO == null">
				<h3>ユーザー情報はありません</h3>
			</s:if>
			<s:elseif test="message"== null">
				<h3>ユーザー情報は以下になります。</h3>
				<table border="1">
					<tr>
						<th>id</th>
						<td><s:property value="userInfoDTO.id" /></td>
					</tr>
					<tr>
						<th>ログインid</th>
						<td><s:property value="userInfoDTO.loginId" /></td>
					</tr>
					<tr>
						<th>パスワード</th>
						<td><s:property value="userInfoDTO.password" /></td>
					</tr>
					<tr>
						<th>ユーザー名</th>
						<td><s:property value="userInfoDTO.userName" /></td>
					</tr>
					<tr>
						<th>登録日</th>
						<td><s:property value="userInfoDTO.insert_date" /></td>
					</tr>
					<tr>
						<th>更新日</th>
						<td><s:property value="userInfoDTO.update_date" /></td>
					</tr>
				</table>
				<s:form action="UserDeleteConfirmAction">
					<s:submit value="削除" />
					<s:hidden name="loginId" value="%{loginId}" />
					<!--%{}とは？Struts2のparamタグの、ognl（おぐぬる）。
					複数商品のうち、ボタンを押したものだけを開く。今回の場合、 loginIdで判断 -->
				</s:form>
			</s:elseif>
			<div id="text-right">
				<p>
					管理者画面へ戻る場合は<a href="admin.jsp">こちら</a>
				</p>
			</div>
		</div>
	</div>
	<div id="footer">
		<div id="pr"></div>
	</div>

</body>
</html>