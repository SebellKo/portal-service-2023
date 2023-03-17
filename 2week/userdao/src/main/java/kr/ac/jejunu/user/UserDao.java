package kr.ac.jejunu.user;

import kr.ac.jejunu.user.User;

import java.sql.*;

import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDao {
    public User findById(Long id) throws ClassNotFoundException, SQLException {
        // 데이터 = mysql
        // mysql 클래스 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Connection 맺고
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306", "root", "rootsejong");
        // 쿼리 만들고
        PreparedStatement preparedStatement = connection.prepareStatement
                ("select id, name, password from userinfo where ?");
        preparedStatement.setLong(1, id);
        // 쿼리 실행하고
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        // 결과를 사용자 정보에 매핑하고
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        // 자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
        // 결과리턴
        return user;
    }
}
