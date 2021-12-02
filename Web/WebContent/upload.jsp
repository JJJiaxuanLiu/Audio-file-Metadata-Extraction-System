<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Upload</title>
	<meta name="keywords" content="Upload">
	<meta name="content" content="Upload">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
 	<link type="text/css" rel="stylesheet" href="css/login.css">
</head>

<body class="login_bj">
<div class="zhuce_body">

	<div class="zhuce_kong login_kuang">
	<div class="zc">
	<div class="bj_bai">
	<h1>Upload Mp3 File</h1><br>
	<form action="UploadServlet" method="post" enctype="multipart/form-data">
		UploadFile: <input type="file" name="music" class="kuang_txt"><br>
		<input type="submit" value="submit" class="btn_zhuce">
	</form>
	</div>
	</div>
	</div>
</div>
</body>
</html>