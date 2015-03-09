package com.epam.dao.H2;

import com.epam.dao.DaoException;
import com.epam.dao.AnnouncementDao;
import com.epam.entity.Announcement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2AnnouncementDao implements AnnouncementDao {
    private static final String SELECT_BY_ID = "SELECT * FROM ANNOUNCEMENT WHERE ID=?;";
    private static final String INSERT = "INSERT INTO ANNOUNCEMENT(TITLE_EN, BODY_EN, TITLE_RU, BODY_RU) " +
                                         "VALUES (?, ?, ?, ?)";
    private static String SELECT_ALL = "SELECT * FROM ANNOUNCEMENT;";
    private static String DELETE_BY_ID = "DELETE ANNOUNCEMENT WHERE ID=?";
    private Connection connection;

    public H2AnnouncementDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Announcement insert(Announcement announcement) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, announcement.getTitleEn());
            statement.setString(2, announcement.getBodyEn());
            statement.setString(3, announcement.getTitleRu());
            statement.setString(4, announcement.getBodyRu());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) throw new DaoException("inserting announcement failed, no rows affected");
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) announcement.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return announcement;
    }

    @Override
    public void update(Announcement entity) throws DaoException {

    }

    @Override
    public void deleteById(Long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0)
                throw new DaoException("deleting announcement failed, no rows affected");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Announcement findById(Long id) throws DaoException {
        Announcement announcement = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) announcement = getAnnouncementBean(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return announcement;
    }

    @Override
    public List findAll() throws DaoException {
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
                List<Announcement> announcements = new ArrayList<>();
                while (resultSet.next()) {
                    announcements.add(getAnnouncementBean(resultSet));
                }
                return announcements;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Announcement> findLastWithLimit(int amount) throws DaoException {
        String SELECT_LAST_WITH_LIMIT = "SELECT * FROM ANNOUNCEMENT GROUP BY ID LIMIT ";
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_LAST_WITH_LIMIT + amount)) {
                List<Announcement> announcements = new ArrayList<>();
                while (resultSet.next()) {
                    announcements.add(getAnnouncementBean(resultSet));
                }
                return announcements;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Announcement getAnnouncementBean(ResultSet resultSet) throws SQLException {
        Announcement announcement = new Announcement();
        announcement.setId(resultSet.getLong("ID"));
        announcement.setTitleEn(resultSet.getString("TITLE_EN"));
        announcement.setBodyEn(resultSet.getString("BODY_EN"));
        announcement.setTitleRu(resultSet.getString("TITLE_RU"));
        announcement.setBodyRu(resultSet.getString("BODY_RU"));
        announcement.setPostDate(resultSet.getTimestamp("POST_DATE"));
        return announcement;
    }
}
