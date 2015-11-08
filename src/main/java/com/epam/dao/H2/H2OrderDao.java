package com.epam.dao.H2;

import com.epam.dao.DaoException;
import com.epam.dao.OrderDao;
import com.epam.dao.Page;
import com.epam.entity.*;
import com.epam.entity.Driver;

import java.sql.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;


public class H2OrderDao implements OrderDao {
    private Connection connection;
    private static final String INSERT = "INSERT INTO ORDERS VALUES(NULL,?,?,?,?,DEFAULT,?,?,?,NULL)";
    private static final String UPDATE_DRIVER_COST_STATUS_DISPATCHER = "UPDATE ORDERS SET DRIVER_ID=?, " +
            "COST=?, STATUS_ID=?, DISPATCHER_ID=COALESCE(?, DISPATCHER_ID) WHERE ID=?";
    private static final String JOIN = " INNER JOIN STATUS ON STATUS.ID=ORDERS.STATUS_ID ";
    private static final String SELECT_BY_ID = "SELECT * FROM ORDERS" + JOIN + "WHERE ORDERS.ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM ORDERS" + JOIN;
    private static final String SELECT_NOT_COMPLETED_BY_DRIVER_ID = "SELECT * FROM ORDERS" + JOIN +
            "WHERE DRIVER_ID=? AND STATUS_ID<>6";
    private static final String SELECT_NOT_COMPLETED_BY_CLIENT_ID = "SELECT * FROM ORDERS" + JOIN +
            "WHERE CLIENT_ID=? AND STATUS_ID<>6";
    private static final String SELECT_EXPECTING_BY_CLIENT_ID = "SELECT * FROM ORDERS" + JOIN +
            "WHERE CLIENT_ID=? AND (STATUS_ID=4 OR STATUS_ID=2)";
    private static final String SELECT_TAKEN_UP_BY_DRIVER_ID = "SELECT * FROM ORDERS" + JOIN +
            "WHERE DRIVER_ID=? AND STATUS_ID>2 AND STATUS_ID<6";
    private static final String SELECT_NOT_COMPLETED = "SELECT * FROM ORDERS" + JOIN + "WHERE STATUS_ID<>6";
    private static final String DELETE_DRIVER_ID_BY_ID = "UPDATE ORDERS SET DRIVER_ID=NULL WHERE ID=?";
    private static final String DELETE_BY_ID = "DELETE FROM ORDERS WHERE ID=?";
    private static final String SELECT_RECEIVED_TIME_BY_DISPATCHER_ID = "SELECT DISTINCT " +
            "YEAR(RECEIVED_TIME), MONTH(RECEIVED_TIME) FROM ORDERS" + JOIN + "WHERE DISPATCHER_ID=?";
    private static final String SELECT_RECEIVED_TIME_BY_DRIVER_ID = "SELECT DISTINCT YEAR(RECEIVED_TIME), " +
            "MONTH(RECEIVED_TIME) FROM ORDERS" + JOIN + "WHERE DRIVER_ID=?";
    private static final String SELECT_BY_DRIVER_ID = "SELECT * FROM ORDERS" + JOIN +
            "WHERE DRIVER_ID=?";
    private static final String SELECT_BY_DISPATCHER_ID = "SELECT * FROM ORDERS" + JOIN +
            "WHERE DISPATCHER_ID=?";
    private final String SELECT_WITH_LIMIT = "SELECT * FROM ORDERS" + JOIN + "ORDER BY ORDERS.ID DESC LIMIT ";
    private final String SELECT_NOT_SERVED_WITH_LIMIT = "SELECT * FROM ORDERS" +
            JOIN + "WHERE STATUS_ID = 1 OR STATUS_ID IS NULL ORDER BY ORDERS.ID DESC LIMIT ";
    private static final String SELECT_ORDERS_COUNT = "SELECT COUNT(*) FROM ORDERS";
    private static final String SELECT_NOT_SERVED_ORDERS_COUNT = "SELECT COUNT(*) FROM ORDERS " +
            "WHERE STATUS_ID = 1 OR STATUS_ID IS NULL";

    public H2OrderDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * @param order
     * @return generated id of inserted row in table
     * @throws DaoException
     */
    @Override
    public Order insert(Order order) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getClient().getId());
            statement.setString(2, order.getCarClass().toString());
            statement.setString(3, order.getPickupLocation());
            statement.setString(4, order.getDropOffLocation());
            if (order.getDriver() == null)
                statement.setNull(5, Types.BIGINT);
            else
                statement.setLong(5, order.getDriver().getId());
            if (order.getCost() == null)
                statement.setNull(6, Types.DECIMAL);
            else
                statement.setBigDecimal(6, order.getCost());
            statement.setInt(7, order.getStatus().getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("inserting flight failed, no rows affected");
            }
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    Long id = resultSet.getLong(1);
                    order.setId(id);
                } else {
                    throw new SQLException("inserting flight failed, no ID obtained");
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return order;
    }

    /**
     * updates row in table with given fields of Order bean
     *
     * @param order
     * @throws DaoException
     */
    @Override
    public void update(Order order) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_DRIVER_COST_STATUS_DISPATCHER)) {
            if (order.getDriver() == null)
                statement.setNull(1, Types.BIGINT);
            else
                statement.setLong(1, order.getDriver().getId());
            if (order.getCost() == null)
                statement.setNull(2, Types.DECIMAL);
            else
                statement.setBigDecimal(2, order.getCost());
            if (order.getStatus() == null)
                statement.setNull(3, Types.INTEGER);
            else
                statement.setInt(3, (order.getStatus().getId()));
            if (order.getDispatcher() != null) {
                statement.setObject(4, order.getDispatcher().getId());
            } else statement.setNull(4, Types.BIGINT);
            statement.setLong(5, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * @param id
     * @return orders collection where column 'status' not equals to 'completed' by id of given Driver
     * @throws DaoException
     */
    @Override
    public List<Order> findNotCompletedByDriverId(Long id) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_NOT_COMPLETED_BY_DRIVER_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) orders.add(getOrderBean(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    /**
     * @param id
     * @return orders collection where column 'status' not equals to 'completed' by id of given Client
     * @throws DaoException
     */
    @Override
    public List<Order> findNotCompletedByClientId(Long id) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_NOT_COMPLETED_BY_CLIENT_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    orders.add(getOrderBean(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    /**
     * @return orders collection where column 'status' not equals to 'completed'
     * @throws DaoException
     */
    @Override
    public List<Order> findNotCompleted() throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_NOT_COMPLETED)) {
            while (resultSet.next()) {
                orders.add(getOrderBean(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    /**
     * remove entry from table 'driver' by given id
     *
     * @param driver
     * @throws DaoException
     */
    @Override
    public void removeDriver(Driver driver) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_DRIVER_ID_BY_ID)) {
            statement.setLong(1, driver.getCurrentOrder().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> findExpectingByClientId(Long id) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_EXPECTING_BY_CLIENT_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    orders.add(getOrderBean(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public Page<Order> paginateAll(Page<Order> page) throws DaoException {
        try {
            setPageAttrs(page, SELECT_WITH_LIMIT, SELECT_ORDERS_COUNT);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return page;
    }

    @Override
    public Page<Order> paginateNotServed(Page<Order> page) throws DaoException {
        try {
            setPageAttrs(page, SELECT_NOT_SERVED_WITH_LIMIT, SELECT_NOT_SERVED_ORDERS_COUNT);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return page;
    }

    @Override
    public Order findTakenUpByDriverId(Long id) throws DaoException {
        Order order = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_TAKEN_UP_BY_DRIVER_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    order = getOrderBean(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return order;
    }

    @Override
    public List<YearMonth> findMonthsByDispatcherId(Long id) throws DaoException {
        List<YearMonth> months = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_RECEIVED_TIME_BY_DISPATCHER_ID)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    YearMonth yearMonth = YearMonth.of(rs.getInt(1), rs.getInt(2));
                    months.add(yearMonth);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return months;
    }

    @Override
    public List<YearMonth> findMonthsByDriverId(Long id) throws DaoException {
        List<YearMonth> months = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_RECEIVED_TIME_BY_DRIVER_ID)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    YearMonth yearMonth = YearMonth.of(rs.getInt(1), rs.getInt(2));
                    months.add(yearMonth);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return months;
    }

    @Override
    public List<Order> findByDriverId(Long id) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_DRIVER_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    orders.add(getOrderBean(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public List<Order> findByDispatcherId(Long id) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_DISPATCHER_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    orders.add(getOrderBean(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    /*private YearMonth getYearMonth(Timestamp timestamp) {
        long time = timestamp.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        YearMonth yearMonth = YearMonth.of(year, month);
        return yearMonth;
    }*/

    @Override
    public void deleteById(Long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0)
                throw new DaoException("deleting order failed, no rows affected");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Order findById(Long id) throws DaoException {
        Order order = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) order = getOrderBean(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return order;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
            while (resultSet.next()) {
                orders.add(getOrderBean(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    /**
     * @param resultSet
     * @return created bean with initialized fields accordingly with ResultSet
     * @throws java.sql.SQLException
     */
    public Order getOrderBean(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("ID"));
        order.setCarClass(CarClass.valueOf(resultSet.getString("CAR_CLASS")));
        order.setPickupLocation(resultSet.getString("PICKUP_LOCATION"));
        order.setDropOffLocation(resultSet.getString("DROP_OFF_LOCATION"));
        order.setReceivedTime(resultSet.getTimestamp("RECEIVED_TIME"));
        order.setStatus(resultSet.getString("VALUE"));
        order.setCost(resultSet.getBigDecimal("COST"));
        return order;
    }


    private void setPageAttrs(Page<Order> page, String ordersQuery, String countQuery) throws SQLException {
        ordersQuery = ordersQuery + page.getCountPerPage() +
                " OFFSET " + page.getCountPerPage() * page.getPageNumber();
        List<Order> orders = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(ordersQuery)) {
                while (resultSet.next()) {
                    orders.add(getOrderBean(resultSet));
                }
                page.setItems(orders);
            }
            try (ResultSet resultSet = statement.executeQuery(countQuery)) {
                int ordersCount = 0;
                if (resultSet.next()) ordersCount = resultSet.getInt(1);
                float pagesCount = (float)ordersCount / page.getCountPerPage();
                if (pagesCount % 1 == 0 && pagesCount == page.getPageNumber() + 1)
                    page.setLast(true);
                else if (Math.round(pagesCount) == page.getPageNumber()) {
                    page.setLast(true);
                } else page.setLast(false);
            }
        }
    }
}
