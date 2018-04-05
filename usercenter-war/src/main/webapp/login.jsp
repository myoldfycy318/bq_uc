<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form id="form1" name="form1"  action="/login/doWebLogin">
	<table align="center">
		<tralign="center">
			<td cosl="2">登录页面<td>
		</tr>
		<tr>
			<td>帐号:</td>
			<td><input type="text" name="tel" id="tel" size="30"></td>
		</tr>
		<tr>
			<td>密码:</td>
			<td><input type="text" name="password" id="password" size="30"></td>
		</tr>
		<tr>
			<td>验证码:</td>
			<td><img src="/jcaptcha" height="100px" width="130px"  onclick="this.src='/jcaptcha?d='+new Date()*1">
			<input type='text' name='j_captcha_response' value=''></td>
		</tr>
		<tralign="center">
			<td cosl="2"> <input type="submit" value="提交"></td>
		</tr>
	</table>
</form>
</body>
</html>