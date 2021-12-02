package org.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dao.LoginDao;
import org.entity.User;

public class LoginServlet extends HttpServlet {

	//controller layer, receive requirement from view layer and send to model layer
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//login require
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd");
		User user = new User(name, pwd);

		//Calling the login function of the model layer
		int result = LoginDao.query(user);
		if (result > 0) {			//login succeeded, jumping to upload.jsp
			response.sendRedirect("upload.jsp");
		} else {					//failed to login
			response.sendRedirect("login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
