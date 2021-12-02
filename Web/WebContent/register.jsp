<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>register</title>
	<meta name="keywords" content="register">
	<meta name="content" content="register">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <link type="text/css" rel="stylesheet" href="css/login.css">
    
</head>

<body class="login_bj">
<div class="zhuce_body">
	<div class="zhuce_kong login_kuang">
	<div class="zc">
	<div class="bj_bai">
	<h1>Metadata Extraction System</h1>
	<h3>Register</h3>
	<form action="RegisterServlet" method="post">
		user name:<input type="text" name="uname" class="kuang_txt"><br/>
		password:<input type="password" name="upwd" class="kuang_txt"><br/>
		<input type="submit" value="register" class="btn_zhuce"><br>
	</form>
	</div>
	</div>
	</div>

</div>
</body>
</html>