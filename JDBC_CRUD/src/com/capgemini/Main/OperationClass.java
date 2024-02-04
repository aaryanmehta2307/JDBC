package com.capgemini.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class OperationClass {
	Scanner sc=new Scanner(System.in);
	Connection con=null;
	ConnectionClass conObject=new ConnectionClass();
	int count=0;
	public void insert_record() throws ClassNotFoundException, SQLException {
		
		System.out.println("Enter student UID:");
		String StudentUID=sc.next();
		System.out.println("Enter student Contact:");
		 int  StudentContact=sc.nextInt();
		System.out.println("Enter student Name:");
		String StudentName=sc.next();
		System.out.println("Enter student Course: ");
		String Studentccourse=sc.next();
		if(con==null) {
			con=conObject.getConnection();
		}
		
		String countStatus="select max(idstudent) from Student";
		PreparedStatement psmt=con.prepareStatement(countStatus);
		ResultSet rs=psmt.executeQuery();
		if(rs.next()) {
			count=rs.getInt(1);
		}
		String insertQuery="insert into student values(?,?,?,?,?)";
		
		
		PreparedStatement psmt2=con.prepareStatement(insertQuery);
		count+=1;
		psmt2.setInt(1,count );
	
		psmt2.setString(2,StudentUID );
		psmt2.setInt(3,StudentContact);		
		psmt2.setString(4,StudentName);
		psmt2.setString(5,Studentccourse);
		
		int status=psmt2.executeUpdate();
		if(status>0) {
			System.out.println("Rcord inserted Successfully");
			
		}
		else {
			System.out.println("Record not inserted");
			
		}
		psmt.close();
		psmt2.close();
		
		
		
	}
	
	public void update() throws ClassNotFoundException, SQLException {
		System.out.println("Enter StudentUID:");
		String StudentUID=sc.next();
		
		String searchQuery="select *from Student where StudentUID=?";
		con=conObject.getConnection();
		PreparedStatement psmt1=con.prepareStatement(searchQuery);
		psmt1.setString(1,StudentUID);
		
		ResultSet rs=psmt1.executeQuery();
		if(rs.next()) {
			System.out.println("Student UID:"+rs.getString(2));
			System.out.println("Student Contact:"+rs.getInt(3));
			System.out.println("Student Name:"+rs.getString(4));
			System.out.println("Student course:"+rs.getString(5));
			
		}
		else {
			System.out.println("Record not found");
		}
//		System.out.println("Enter the attribute you want to change \n 1. Contact \n 2.Name \n 3. Course  ");
		
		System.out.println("Enter updated course:");
		String updatecourse=sc.next();
		
		String updateQuery="update student set Studentccourse=? where StudentUID=?";
		PreparedStatement psmt2=con.prepareStatement(updateQuery);
		psmt2.setString(1, updatecourse);
		psmt2.setString(2, StudentUID);
		 
		int status=psmt2.executeUpdate();
		if(status>0) {
			System.out.println("Record updated successfully");
			
		}
		psmt1.close();
		psmt2.close();
		con.close();
		
	}
	
	public void delete() throws ClassNotFoundException, SQLException {
		System.out.println("Enter StudentUID:");
		String StudentUID=sc.next();
		
		String searchQuery="select *from Student where StudentUID=?";
		con=conObject.getConnection();
		PreparedStatement psmt1=con.prepareStatement(searchQuery);
		psmt1.setString(1,StudentUID);
		
		ResultSet rs=psmt1.executeQuery();
		if(rs.next()) {
			System.out.println("Student UID:"+rs.getString(2));
			System.out.println("Student Contact:"+rs.getInt(3));
			System.out.println("Student Name:"+rs.getString(4));
			System.out.println("Student course:"+rs.getString(5));
			
		}
		else {
			System.out.println("Record not found");
		}
		
		System.out.println("are you sure you want to delete the data? 1/yes 0/no");
		int reply=sc.nextInt();
		if(reply==1) {
			String deleteQuery="delete from Student where StudentUID=?";
			PreparedStatement psmt2=con.prepareStatement(deleteQuery);
			psmt2.setString(1, StudentUID);
			
			int status1=psmt2.executeUpdate();
			if(status1>0) {
				System.out.println("Record deleted Successfully");
			}
			else {
				System.out.println("No record deleted");
			}
			psmt1.close();
			psmt2.close();
			con.close();
		}
			
		}
		
		public void display_one() throws ClassNotFoundException, SQLException {
			System.out.println("Enter StudentUID:");
			String StudentUID=sc.next();
			
			String searchQuery="select *from Student where StudentUID=?";
			con=conObject.getConnection();
			PreparedStatement psmt1=con.prepareStatement(searchQuery);
			psmt1.setString(1,StudentUID);
			
			ResultSet rs=psmt1.executeQuery();
			if(rs.next()) {
				System.out.println("Student UID:"+rs.getString(2));
				System.out.println("Student Contact:"+rs.getInt(3));
				System.out.println("Student Name:"+rs.getString(4));
				System.out.println("Student course:"+rs.getString(5));
				
			}
			else {
				System.out.println("Record not found");
			}
			
	}
		public void display_all() throws ClassNotFoundException, SQLException {
			
			String searchQuery="select *from Student";
			con=conObject.getConnection();
			PreparedStatement psmt1=con.prepareStatement(searchQuery);
			
			ResultSet rs=psmt1.executeQuery();
			while(rs.next()) {
				System.out.println("Student UID:"+rs.getString(2));
				System.out.println("Student Contact:"+rs.getInt(3));
				System.out.println("Student Name:"+rs.getString(4));
				System.out.println("Student course:"+rs.getString(5));
				
			}
			
			
		}
}
