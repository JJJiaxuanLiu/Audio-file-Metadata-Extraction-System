package com.test;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.dao.MetadataDao;
import org.dao.RegisterDao;
import org.junit.jupiter.api.Test;

//testing RegisterDao
class RegisterDaoTest {

	//testing connection() method
	@Test
	void testConnction() {
		Connection connection = RegisterDao.connction();
		System.out.println(connection);
	}

	
	//testing update() method
	@Test
	void testUpdate() {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = RegisterDao.connction();
		
			statement = connection.createStatement();
			String sql = "insert into metadata values('usernametest','passwordtest')";
			int result = statement.executeUpdate(sql);
			if(result > 0) {
				System.out.println("Update successed!");
			}else {
				System.out.println("Update Failed!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(statement != null)statement.close();
				if(connection != null)connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	

}
