// If you're using a package, uncomment the following line and replace with your actual package name
// package com.example;
package com.blockhain;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class Database{
	protected String previous_hash;
	protected String hash;
	protected long timestamp;
	protected String data;
	protected ArrayList<String> temp_data = new ArrayList<String>();
	private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String user = "postgres";
    private String password = "Oluwaseun123";
    public Database() {
    	
    }
	public Database(String previous_hash, String hash,long timestamp,String data) {
		this.previous_hash = previous_hash;
		this.hash = hash;
		this.timestamp = timestamp;
		this.data = data;   
	}
	protected void insert() {
		final String insertsql = "INSERT INTO Blockchain (previous_hash,hash,timestamp,data) VALUES (?,?,?,?)";
		try {
            // Explicitly load the JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish the connection
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
            // Prepare the SQL INSERT statement
            PreparedStatement pstmt = conn.prepareStatement(insertsql);
            pstmt.setString(1, this.previous_hash); // Set the value for the first placeholder (?)
            pstmt.setString(2, this.hash); // Set the value for the second placeholder (?)
            pstmt.setLong(3, this.timestamp);
            pstmt.setString(4, this.data);

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
	protected ArrayList<String> getinsert() {
		String query = "SELECT previous_hash,hash FROM Blockchain ORDER BY id DESC LIMIT 1"; // Replace "id" with your actual column
		String hash = "";
		String prevhash = "";
        // Establishing the connection
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Processing the result set for the last row
            if (rs.next()) {
                prevhash = rs.getString("previous_hash");
                hash = rs.getString("hash");
                temp_data.add(prevhash);
                temp_data.add(hash);

            } else {
                System.out.println("No rows found.");
            }

        } catch (SQLException e) {
        	e.printStackTrace();e.printStackTrace();          
        }
        return temp_data;
	}
}