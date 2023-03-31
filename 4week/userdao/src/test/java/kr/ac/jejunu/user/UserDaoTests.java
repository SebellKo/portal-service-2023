package kr.ac.jejunu.user;

import kr.ac.jejunu.user.UserDao;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    private static UserDao userDao;
    @BeforeAll
    public static void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }
    @Test // 테스트 대상 메소드임을 지정
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "sejong";
        String password = "1234";
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id)); // assertThat = 테스트 메소드 / user.getId()의 결과가 id와 같은지 확인.
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "sejong";
        String password = "1234";
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(1l));

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));

    }

//    @Test // 테스트 대상 메소드임을 지정
//    public void getForHalla() throws SQLException, ClassNotFoundException {
//        Long id = 1l;
//        String name = "sejong";
//        String password = "1234";
//        ConnectionMaker connectionMaker = new HallaConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
//        User user = userDao.findById(id);
//        assertThat(user.getId(), is(id)); // assertThat = 테스트 메소드 / user.getId()의 결과가 id와 같은지 확인.
//        assertThat(user.getName(), is(name));
//        assertThat(user.getPassword(), is(password));
//    }
//
//    @Test
//    public void insertForHalla() throws SQLException, ClassNotFoundException {
//        User user = new User();
//        String name = "sejong";
//        String password = "1234";
//        user.setName(name);
//        user.setPassword(password);
//        ConnectionMaker connectionMaker = new HallaConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
//        userDao.insert(user);
//        assertThat(user.getId(), greaterThan(1l));
//
//        User insertedUser = userDao.findById(user.getId());
//        assertThat(insertedUser.getId(), is(user.getId()));
//        assertThat(insertedUser.getName(), is(name));
//        assertThat(insertedUser.getPassword(), is(password));
//
//    }
}