<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Login to checkNumber to Word</h1>
${SPRING_SECURITY_LAST_EXCEPTION.message}
<form action="login" method="post">
<table>
<tr><td>Enter login name</td><td><input type="text" name="username" value=""></td></tr>
<tr><td>Password</td><td><input type="password" name="password" value=""></td></tr>
<tr><td>Submit</td><td><input type="submit" name="submit" value="submit"></td></tr>
</table>
</form>
</body>
</html>