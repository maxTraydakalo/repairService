package com.traydakalo.dao.queries;

import com.traydakalo.dao.DaoException;
import com.traydakalo.dao.DataBaseUtility;
import com.traydakalo.dto.UserDto;
import com.traydakalo.entity.User;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserDaoInterface {


    // Constants ----------------------------------------------------------------------------------
    private static final String SQL_FIND_BY_ID =
            "SELECT * FROM users where id=?";

    private static final String SQL_LIST_ORDER_BY_ID =
            "SELECT * FROM users ORDER BY id";

    private static final String SQL_FIND_BY_LOGIN =
            "select ua.id, ua.login, ua.password, user_roles.role\n" +
                    "from user_roles\n" +
                    "         join user_grants ug on user_roles.id = ug.user_role_id\n" +
                    "         join users ua on ug.user_id = ua.id\n" +
                    "where ua.login = ?";

    private static final String SQL_INSERT_INTO_USERS =
            "insert into userS (login, password) values (?,?)";

private static final String SQL_INSERT_ROLE =
            "insert into user_grants (user_id, user_role_id) " +
                    "values ((select id from users where login=?)," +
                    " (select id from user_roles where role = ?))";




    // Vars ---------------------------------------------------------------------------------------
    private BasicDataSource dataSource;
    private static UserDao userDao;

    private UserDao() {
        dataSource = DataBaseUtility.getDataSource();
    }
    // Constructors -------------------------------------------------------------------------------


    public static UserDao getUserDao() {
        if (userDao == null) {
            synchronized (UserDao.class) {
                if (userDao == null) {
                    userDao = new UserDao();
                }
            }
        }
        return userDao;
    }

    @Override
    public UserDto find(Long id) throws DaoException {
        UserDto userDto = new UserDto();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    userDto.setId(resultSet.getLong("id"));
                    userDto.setLogin(resultSet.getString("login"));
                    userDto.setPassword(resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userDto;
    }

    @Override
    public User find(String login) throws DaoException {
        User user = new User();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_LOGIN)
        ) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.addRole(resultSet.getString("role"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public List<User> list() throws DaoException {
        List<User> users = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public void register(String login, String password) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INTO_USERS)
        ) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void addRole(String login, String role) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ROLE)
        ) {
            statement.setString(1, login);
            statement.setString(2, role);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
