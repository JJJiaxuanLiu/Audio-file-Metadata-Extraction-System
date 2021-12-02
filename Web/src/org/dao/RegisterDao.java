package org.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.entity.User;

public class RegisterDao {
	static Connection connection = null;

	// connect to database
	public static Connection connction() {
		try {
			Class.forName("org.postgresql.Driver");// loading driving class
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not find JDBC Driver");
			e.printStackTrace();
		}
		try {
			// connect to database
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "postgres");
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection Failed!");
			e.printStackTrace();
		}
		return null;
	}

	// update entity of database
	public static int update(User user) {
		connction();
		PreparedStatement preparedstatement = null;
		if (connection != null) {
			System.out.println("Controlling your database...");
			try {
				String sql = "";
				sql = "insert into userinfo(username,password) values(?,?)";
				preparedstatement = connection.prepareStatement(sql);
				preparedstatement.setString(1, user.getUsername());
				preparedstatement.setString(2, user.getPassword());

				int status = preparedstatement.executeUpdate(); //execute update sql, searching method is exeecuteUpate()

				if (status == 1) {
					System.out.println("update succeeded");
					return 1;
				} else {
					System.out.println("NO update done");
					return 0;
				}
				

			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}finally {
				try {
					preparedstatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		} else {
			System.out.println("Failed to establish connection...");
			return -1;
		}
	}

}
