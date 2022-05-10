package com.skit.learning.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class UserDao {
	static String url="jdbc:postgresql://localhost:5432/postgres";
	static String username="postgres";
	static String password="sambit@123";
	
	private Connection connection;
	
	public UserDao() throws ClassNotFoundException, SQLException {
		connection = getConnection();
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		System.out.println("Driver class loaded!");
		Connection connection = DriverManager.getConnection(url, username, password);
		System.out.println("Connection established!");
		return connection;
	}
	
	public int createUser(String email, String name, String password) throws SQLException {
		int row = 0;
		Statement statement= this.connection.createStatement();
		String query="INSERT INTO usersignup (email, name, password) values('"+ email +"', '"+name+"', '"+password+"')";
		System.out.println(query);
		row = statement.executeUpdate(query);
		System.out.println(row+" row/rows created!");	
		return row;
	}

	public Map<String, String> fetchUser(String email, String psw) throws SQLException {
		Statement statement= this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String queryWithPlaceHolder="SELECT * FROM USERSIGNUP WHERE email ='%s'";
		String query = String.format( queryWithPlaceHolder, email, psw);
		System.out.println(query);
		ResultSet row = statement.executeQuery(query);
		Map<String, String> resultMap = new HashMap<>();
		if(row.next()) {	
			String fetchEmail = row.getString(1);
			String fetchName=  row.getString(2);
			String password= row.getString(3);
			if(password.equals(psw)) {
				resultMap.put("username", fetchName);
			}else {
				resultMap = null;
			}
		}else {
			System.out.println("Invalid user id");
		}
		return resultMap;
	}
}
