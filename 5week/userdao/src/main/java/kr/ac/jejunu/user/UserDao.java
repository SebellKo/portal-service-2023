package kr.ac.jejunu.user;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User findById(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new FindStatementStrategy(id);
        return jdbcContext.jdbcContextForFind(statementStrategy);
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new InsertStatementStrategy(user);
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }

    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(long id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}
