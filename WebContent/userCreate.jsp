<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<title>UserCreate画面</title>
</head>
<body>
	<div id="header"></div>
	<div id="main">
		<div id="top">
			<p>UserCreate</p>
		</div>
		<div>
			<!-- 下記のif文は、UserCreateConfirmActionからstruts.xmlにerrorが返された時、つまり
            入力枠のどれか1つでも空の場合に走る処理。UserCreateConfirmActionでerrorとなる場合、変数errorMessageには値が
            格納される。そして、再度このページを表示するときに、変数errorMessageは空でない、つまり値が入っている状態なので
            処理が走る -->
			<s:if test="errorMessage != ''">
				<s:property value="errorMessage" escape="false" />

			</s:if>
			<table>
				<s:form action="UserCreateConfirmAction">
					<tr>
						<td><label>ログインID:</label></td>
						<td><input type="text" name="loginUserId" value="" /></td>
					</tr>
					<tr>
						<td><label>ログインPASS:</label></td>
						<td><input type="text" name="loginPassword" value="" /></td>
					</tr>
					<tr>
						<td><label>ユーザー名:</label></td>
						<td><input type="text" name="userName" value="" /></td>
					</tr>
					<s:submit value="登録" />
				</s:form>
			</table>
			<div>
				<p>
					前画面に戻る場合は<a href='<s:url action="GoHomeAction" />'>こちら</a>
				</p>
				<p>
					マイページは <a href='<s:url action="MyPageAction" />'>こちら</a>
				</p>
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>