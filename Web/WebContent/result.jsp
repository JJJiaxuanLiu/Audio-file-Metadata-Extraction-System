<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.back1{
		background: url("img/background.jpg") no-repeat;
		height:100%;
		width:100%;
		background-size:cover;
	}
	
</style>
</head>
<body class="back1">
<div class="back1" align="center">
<h1>Result:
<%	
		String message = (String)request.getAttribute("message");
		if(message.contains(",")){
			String[] parts = message.split("\\,");
			for(String part : parts){
				out.println(part);%><br><%
			}
		}else{
			out.print(message);
		}
		
			
%>
</h1>
</div>
	
</body>
</html>