package com.blockhain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
public class Main_test {
	public static void main(String[] args){
		System.out.println("Hello World");
		 String url = "jdbc:postgresql://localhost:5432/postgres";
	        String user = "postgres";
	        String password = "Oluwaseun123";
	        String insertSQL = "INSERT INTO sales (name, age) VALUES (?, ?)";

	        try {
	            // Explicitly load the JDBC driver
	            Class.forName("org.postgresql.Driver");

	            // Establish the connection
	            Connection conn = DriverManager.getConnection(url, user, password);
	            System.out.println("Connected to the PostgreSQL server successfully.");
	         // Prepare the SQL INSERT statement
	            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
	            pstmt.setString(1, "Jeremy"); // Set the value for the first placeholder (?)
	            pstmt.setInt(2, 36); // Set the value for the second placeholder (?)

	            // Execute the insert statement
	            int affectedRows = pstmt.executeUpdate();

	            if (affectedRows > 0) {
	                System.out.println("Data inserted successfully.");
	            } else {
	                System.out.println("Data insertion failed.");
	            }

	            // Close the PreparedStatement and Connection
	            pstmt.close();
	            conn.close();
	        } catch (ClassNotFoundException e) {
	            System.out.println("PostgreSQL JDBC driver not found.");
	            e.printStackTrace();
	        } catch (SQLException e) {
	            System.out.println("Connection failure.");
	            e.printStackTrace();
	        }
	}
}
