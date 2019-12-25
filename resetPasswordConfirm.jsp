<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/resetPassword.css">
<link rel="stylesheet" type="text/css" href="./css/header.css">
<title>パスワード再設定確認画面</title>
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp" />
	</div>

	<div id="main">
		<div id="top">
			<h1>パスワード再設定確認画面</h1>
		</div>

		<div id="contents">
			<s:form action="ResetPasswordCompleteAction">
			<table>
				<tr id="box">
					<th><label>ユーザーID:</label></th>
					<td><s:property value="%{#session.user_id_for_reset_password}"
							escape="false" /></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="新しいパスワード" /></th>
					<td><s:property value="%{#session.concealed_password}" /></td>
				</tr>
			</table>
			<div><s:submit value="パスワード再設定" class="submit" /></div>
			</s:form>

			<s:form action="ResetPasswordAction">
				<div><s:submit value="戻る" class="submit" /></div>
				<s:hidden id="backFlag" name="backFlag" value="1" />
			</s:form>
		</div>
	</div>
	<div id="footer"></div>

</body>
</html>