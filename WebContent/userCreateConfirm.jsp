<!-- ページの設定 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- struts2のカスタムタグ(s:form等)を使えるようにしている -->
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/style.css">

<title>UserCreateConfirm画面</title>

</head>
<body>
	<div id="header"></div>
	<div id="main">
		<div id="top">
			<p>UserCreateonfirm</p>
		</div>
		<div>
			<h3>登録する内容は以下でよろしいですか。</h3>
			<table>
				<s:form action="UserCreateCompleteAction">
					<tr id="box">
						<td><label>ログインID:</label></td>
						<td><s:property value="loginUserId" escape="false" /></td>
					</tr>
					<tr id="box">
						<td><label>ログインPASS:</label></td>
						<td><s:property value="loginPassword" escape="false" /></td>
					</tr>
					<tr id="box">
						<td><label>ユーザー名:</label></td>
						<!-- loginUserIdに格納された値を表示。 -->
                            <!-- escape="false"→表示された値に対して、HTMLのエスケープ処理を無効にしてる
                           	 	エスケープ処理→ある文字列を、使用してる言語において決められた、別の文字列に変換する処理
                            	なぜか→入力された値を表示させるだけだが、もしかすると、エスケープ処理の対象文字が含まれていていて
                            	ソースを上書きされてしまったり、意図的にインジェクション攻撃される恐れがあり、セキュリティ面を考えて-->
						<td><s:property value="userName" escape="false" /></td>
					</tr>
					<tr>
						<!-- 完了という文字列を表示させたボタンを設置。押下すると、UserCreateCompleteActionを探しに
                            struts.xmlを見に行く -->
						<td><s:submit value="完了" /></td>
					</tr>
				</s:form>
			</table>
			</div>
		</div>
	<div id="footer"></div>
</body>
</html>