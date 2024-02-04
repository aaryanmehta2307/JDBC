package com.capgemini.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Connection con=null;
		Class.forName("com.mysql.cj.jdbc.Driver");// load and register the driver
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cuims","root","1234");
		System.out.println(con.getClass().getName());
		System.out.println("Connection Established");
		return con;
	}
}
