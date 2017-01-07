package com.kalistore.dao;


import com.kalistore.model.Address;
import com.kalistore.model.City;
import com.kalistore.model.Product;
import com.kalistore.model.User;
import com.kalistore.utils.DbConnection;

import java.sql.*;

/**
 * Created by kanch on 5/27/2016.
 */
public class UserDao {
    private Connection conn;

    public UserDao() {
        conn = DbConnection.getConnection();
    }

    public void addUser(User user) throws SQLException {
        ResultSet rs = null;

        try {
            if (conn.isClosed()) {
                conn = DbConnection.getConnection();
            }
            int userId;

            PreparedStatement preparedStatement = conn
                    .prepareStatement("INSERT INTO users(username, password, fullName, phone) " +
                            "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.executeUpdate();

            rs = preparedStatement.getGeneratedKeys();
            rs.next();
            userId = rs.getInt(1);

            if (user.getMainAddress() != null) {
                preparedStatement = conn
                        .prepareStatement("INSERT INTO addresses(cityId, addressLine, userId, mainAddress) VALUES (?, ?, ?, 1)");
                preparedStatement.setInt(1, user.getMainAddress().getCity().getCityId());
                preparedStatement.setString(2, user.getMainAddress().getAddressLine());
                preparedStatement.setInt(3, userId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            DbConnection.closeConnection();
        }
    }

    public int validation(User user) throws SQLException {
        int userId = -1;
        ResultSet rs = null;

        try {
            PreparedStatement preparedStatement = conn.
                    prepareStatement("SELECT userId FROM users WHERE username=? AND password=?");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("userId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            DbConnection.closeConnection();
        }

        return userId;
    }

    public User getUserInfo(int userId) throws SQLException {
        User user = new User();
        ResultSet rs = null;

        try {
            if (conn.isClosed()) {
                conn = DbConnection.getConnection();
            }
            PreparedStatement preparedStatement = conn
                    .prepareStatement("SELECT userId, username, fullName, email, phone " +
                            "FROM users " +
                            "WHERE userId=?");
            preparedStatement.setInt(1, userId);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setUserId(rs.getInt("userId"));
                user.setUsername(rs.getString("username"));
                user.setFullName(rs.getString("fullName"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setMainAddress(findMainAddressForUser(userId, rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            DbConnection.closeConnection();
        }

        return user;
    }

    public int facebookLogin(User user) throws SQLException {
        int userId = 0;
        ResultSet rs = null;

        try {
            if (conn.isClosed()) {
                conn = DbConnection.getConnection();
            }
            PreparedStatement checkUser = conn
                    .prepareStatement("SELECT userId " +
                            "FROM users " +
                            "WHERE fbId=?");
            checkUser.setString(1, user.getFbId());
            if (rs.next()) {
                userId = rs.getInt("userId");
            } else {
                PreparedStatement createFbUser = conn
                        .prepareStatement("INSERT INTO users(username, fullName, fbId, fbToken) " +
                                "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                createFbUser.setString(1, user.getUsername());
                createFbUser.setString(2, user.getFullName());
                createFbUser.setString(3, user.getFbId());
                createFbUser.setString(4, user.getFbToken());
                createFbUser.executeUpdate();

                rs = createFbUser.getGeneratedKeys();
                rs.next();
                userId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            DbConnection.closeConnection();
        }

        return userId;
    }

    private Address findMainAddressForUser(int userId, ResultSet rs) throws SQLException {
        PreparedStatement preparedStatement = conn
                .prepareStatement("SELECT addressId, cityId, addressLine " +
                        "FROM addresses " +
                        "WHERE userId=? AND mainAddress=1");
        preparedStatement.setInt(1, userId);
        rs = preparedStatement.executeQuery();
        Address address = new Address();
        while (rs.next()) {
            address.setAddressId(rs.getInt("addressId"));
            address.setCity(CityDao.findCityForId(rs.getInt("cityId"), rs, conn));
            address.setAddressLine(rs.getString("addressLine"));
        }
        return address;
    }
}
