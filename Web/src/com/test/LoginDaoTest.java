package com.test;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.dao.LoginDao;
import org.junit.jupiter.api.Test;

//testing LoginDao class
class LoginDaoTest {

	//testing connection() method
	@Test
	void testConnction() {
		Connection connection = LoginDao.connction();
		System.out.println(connection);
	}

	//testing query() method
	@Test
	void testQuery() {
		Connection connection = null;
		Statement statment = null;
		ResultSet resultSet = null;
		try {
			connection = LoginDao.connction();
		
			statment = connection.createStatement();
		
			String sql = "select * from userinfo";
			resultSet = statment.executeQuery(sql);
		
			while(resultSet.next()) {
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				System.out.println("username="+username+", password="+password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null)resultSet.close();
				if(statment != null)statment.close();
				if(connection != null)connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
