package kr.ac.jejunu.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionMaker {
//    public ConnectionMaker() {
//    }//    abstract public Connection getConnection() throws ClassNotFoundException, SQLException; // 추상화 방법 abstract 를 추가.

    public Connection getConnection() throws ClassNotFoundException, SQLException;
//    public Connection getConnection() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        // Connection 맺고
//        Connection connection = DriverManager.getConnection
//                ("jdbc:mysql://localhost/portalservice", "root", "rootsejong");
//        return connection;
//    }
}