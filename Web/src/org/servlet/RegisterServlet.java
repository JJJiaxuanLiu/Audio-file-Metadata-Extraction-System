package org.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dao.RegisterDao;
import org.entity.User;

public class RegisterServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Handling login requests
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd");
		User user = new User(name, pwd);

		// Calling the registration function of the model layer
		int result = RegisterDao.update(user);
		if (result > 0) {		//register succeeded, jumping to login.jsp
			response.sendRedirect("login.jsp");
		} else {				// Failed to register
			response.sendRedirect("register.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
