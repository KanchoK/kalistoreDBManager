package com.kalistore.dao;


import com.kalistore.model.Address;
import com.kalistore.model.City;
import com.kalistore.model.Product;
import com.kalistore.model.User;
import com.kalistore.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kanch on 5/27/2016.
 */
public class UserDao {
    private Connection conn;

    public UserDao() {
        conn = DbConnection.getConnection();
    }

    public void addUser(User user) {
        try {
            if (conn.isClosed()) {
                conn = DbConnection.getConnection();
            }
            PreparedStatement preparedStatement = conn
                    .prepareStatement("INSERT INTO users(username,password, fullName, email, phone) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
                    .prepareStatement("SELECT fullName, email, phone " +
                            "FROM users " +
                            "WHERE userId=?");
            preparedStatement.setInt(1, userId);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
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
