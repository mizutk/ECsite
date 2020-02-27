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
<title>商品一覧画面</title>
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

table td {
	text-align: left;
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
			<p>itemDetails</p>
		</div>
		<div>
			<s:if test="DTO == null">
				<h3>商品詳細の情報はありません。</h3>
			</s:if>
			<s:elseif test="message == null">
				<h3>商品情報は以下になります。</h3>
				<table border="1">
					<tr>
						<th>id</th>
						<td><s:property value="itemDetailsDTO.id" /></td>
					</tr>

					<tr>
						<th>商品名</th>
						<td><s:property value="itemDetailsDTO.itemName" /></td>
					</tr>

					<tr>
						<th>値段</th>
						<td><s:property value="itemDetailsDTO.itemPrice" /></td>
					</tr>

					<tr>
						<th>在庫</th>
						<td><s:property value="itemDetailsDTO.itemStock" /></td>
					</tr>

					<tr>
						<th>登録日</th>
						<td><s:property value="itemDetailsDTO.insert_date" /></td>
					</tr>

					<tr>
						<th>更新日</th>
						<td><s:property value="itemDetailsDTO.update_date" /></td>
					</tr>
				</table>
				<s:form action="itemListDeleteConfirmAction">
					<s:submit value="削除" />
					<s:hidden name="id" valuse="%{id}" />
				</s:form>
			</s:elseif>
			<div id="text-right">
			<p>商品一覧は<a href='<s:url action="ItemListAction" />'>こちら</a></p>
			</div>
			</div>
		</div>
			<div id="footer">
		<div id="pr"></div>

	</div>

</body>
</html>