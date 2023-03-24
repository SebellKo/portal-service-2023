package kr.ac.jejunu.user;

import java.sql.*;

import java.sql.SQLException;

public class UserDao {
    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User findById(Long id) throws ClassNotFoundException, SQLException {
        // 데이터 = mysql
        // mysql 클래스 로딩
        Connection connection = connectionMaker.getConnection(); // Refactor / Extract / Method
        // 쿼리 만들고
        PreparedStatement preparedStatement = connection.prepareStatement
                ("select id, name, password from userinfo where id = ?");
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

    public void insert(User user) throws ClassNotFoundException, SQLException {
        // 데이터 = mysql
        // mysql 클래스 로딩
        Connection connection = connectionMaker.getConnection();
        // 쿼리 만들고
        PreparedStatement preparedStatement = connection.prepareStatement
                ("insert into userinfo (name, password) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        // 쿼리 실행하고
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        user.setId(resultSet.getLong(1));
        // 자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

//    abstract public Connection getConnection() throws ClassNotFoundException, SQLException; // 추상화 방법 abstract 를 추가.
//    private Connection getConnection() throws ClassNotFoundException, SQLException {
//        // Connection 맺고
//        return connectionMaker.getConnection();
//    }
}


