
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String URL =
            "jdbc:mysql://localhost:3306/hospitality_db";

    private static final String USER = "root";

    private static final String PASSWORD = "muskan";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {

        try {
            Connection con = getConnection();

            if (con != null) {
                System.out.println("Database Connected Successfully!");
            }

            con.close();

        } catch (SQLException e) {
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }
    }
}