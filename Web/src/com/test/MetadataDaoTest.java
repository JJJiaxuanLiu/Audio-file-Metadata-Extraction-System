package com.test;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.dao.MetadataDao;
import org.junit.jupiter.api.Test;

//testing MetadataDao class
class MetadataDaoTest {

	//testing connection() method
	@Test
	void testConnction() {
		Connection connection = MetadataDao.connction();
		System.out.println(connection);
	}

	
	//testing update() method
	@Test
	void testUpdate() {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = MetadataDao.connction();
		
			statement = connection.createStatement();
			String sql = "insert into metadata values('songnametest','artisttest','albumtest','yeartest')";
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
