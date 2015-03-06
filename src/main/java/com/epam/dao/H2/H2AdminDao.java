package com.epam.dao.H2;

import com.epam.dao.AdminDao;
import com.epam.dao.DaoException;
import com.epam.entity.Admin;
import com.epam.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class H2AdminDao implements AdminDao {
    private static final String INSERT = "INSERT INTO ADMIN (LOGIN, PASSWORD, FIRST_NAME, LAST_NAME) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_LOGIN_AND_PASSWORD = "SELECT * FROM ADMIN WHERE LOGIN = ? AND PASSWORD = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM ADMIN WHERE ID = ?";
    private Connection connection;

    public H2AdminDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Admin insert(Admin admin) throws DaoException {
        try (PreparedStatement statement =
                     connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, admin.getLogin());
            statement.setString(2, admin.getPassword());
            statement.setString(3, admin.getFirstName());
            statement.setString(4, admin.getLastName());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("inserting admin failed, no rows affected");
            }
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                
                if (resultSet.next()) {
                    Long id = resultSet.getLong(1);
                    admin.setId(id);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return admin;
    }

    @Override
    public void update(Admin entity) throws DaoException {

    }

    @Override
    public void deleteById(Long id) throws DaoException {

    }

    @Override
    public Admin findById(Long id) throws DaoException {
        Admin admin = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    admin = getClientBean(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return admin;
    }

    @Override
    public List<Admin> findAll() throws DaoException {
        return null;
    }

    @Override
    public Admin findByCredentials(String login, String password) throws DaoException {
        Admin admin = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) admin = getClientBean(resultSet);
            }
        } catch (Exception e) {
            throw new DaoException();
        }
        return admin;
    }

    public Admin getClientBean(ResultSet resultSet) throws SQLException {
        Admin admin = new Admin();
        admin.setId(resultSet.getLong("ADMIN.ID"));
        admin.setFirstName(resultSet.getString("FIRST_NAME"));
        admin.setLastName(resultSet.getString("LAST_NAME"));
        admin.setLogin(resultSet.getString("LOGIN"));
        admin.setPassword(resultSet.getString("PASSWORD"));
        admin.setRole(Role.ADMIN);
        return admin;
    }
}
