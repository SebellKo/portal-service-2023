package kr.ac.jejunu.user;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class JejuConnectionMaker implements ConnectionMaker {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Connection 맺고
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/portalservice", "root", "rootsejong");
        return connection;
    }
}
