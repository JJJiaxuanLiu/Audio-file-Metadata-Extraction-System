package org.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.entity.User;


//model layer, login by searching entity in database
public class LoginDao {
	
	static Connection connection = null;

	//connect to database
	public static Connection connction() {
		try {
			Class.forName("org.postgresql.Driver");//loading driving class
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not find JDBC Driver"); 
			e.printStackTrace();
		}
		try {
			//connect to database
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/","postgres","postgres");
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection Failed!");
			e.printStackTrace();
		}
		return null;
	}
	
	
	//query in database
	public static int query(User user) {
		connction();
		ResultSet resultSet = null;
		PreparedStatement pstatement = null;
		if(connection != null) {
			System.out.println("Controlling your database...");
			try {
				String sql = "";
				sql = "select count(*) from userinfo where username=? and password=?";
				pstatement = connection.prepareStatement(sql);
		
				pstatement.setString(1, user.getUsername());
				pstatement.setString(2, user.getPassword());
				
				resultSet = pstatement.executeQuery();  //access all entities in database store in resultset
				
				if(resultSet != null) {
					//traversing data
					int count = 0;
					if(resultSet.next()) {
						count = resultSet.getInt(1);
					}
					//login succeeded
					if(count>0) {
						System.out.println("login succeeded");
						return count;}
					else {//failed to login
						System.out.println("Failed to login...");
						return count;}
				}else {
					System.out.println("resultSet is null..");
					return -1;}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					resultSet.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					pstatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 0;
		
	}

	
	
}
