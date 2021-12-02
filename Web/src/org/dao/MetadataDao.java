package org.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.entity.Mp3;

//model layer, login by searching in database
public class MetadataDao {
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
	
		//update entity of database
		public static int update(Mp3 mp3) {
			connction();
			PreparedStatement preparedstatement = null;
			if (connection != null) {
				System.out.println("Controlling your database...");
				try {
					String sql = "";
					sql = "insert into metadata(songname,artist,album,year) values(?,?,?,?)";
					preparedstatement = connection.prepareStatement(sql);
					preparedstatement.setString(1, mp3.getSongName());
					preparedstatement.setString(2, mp3.getArtist());
					preparedstatement.setString(3, mp3.getAlbum());
					preparedstatement.setString(4, mp3.getYear());

					int status = preparedstatement.executeUpdate(); // execute update sql, searching method is exeecuteUpate()

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
