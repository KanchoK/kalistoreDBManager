package com.kalistore.dao;


import com.kalistore.model.User;
import com.kalistore.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
                    .prepareStatement("insert into users(email,password) values (?, ?)");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeConnection();
        }
    }

    public void deleteUser(String email) {
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("delete from users where email=?");
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeConnection();
        }
    }
}
