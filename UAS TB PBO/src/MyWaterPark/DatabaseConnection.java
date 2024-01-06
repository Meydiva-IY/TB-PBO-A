package MyWaterPark;

import java.sql.*;
// Test JDBC
class DatabaseConnection { 
    static Connection conn;

    static {
        String url = "jdbc:mysql://localhost:3306/waterpark";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("Connected to the database!");
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver Error");
            System.exit(0);
        } catch (SQLException e) {
            System.err.println("Failed to connect");
        }
    }
}