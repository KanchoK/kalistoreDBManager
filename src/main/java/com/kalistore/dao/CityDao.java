package com.kalistore.dao;

import com.kalistore.model.City;
import com.kalistore.utils.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanch on 12/27/2016.
 */
public class CityDao {
    private Connection conn;

    public CityDao() {
        conn = DbConnection.getConnection();
    }

    public List<City> getAllCities() throws SQLException {
        List<City> cities = new ArrayList<City>();
        ResultSet rs = null;

        try {
            if (conn.isClosed()) {
                conn = DbConnection.getConnection();
            }
            Statement statement = conn.createStatement();
            rs = statement.executeQuery("SELECT cityId, name FROM cities");
            while (rs.next()) {
                City city = new City();
                city.setCityId(rs.getInt("cityId"));
                city.setName(rs.getString("name"));

                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            DbConnection.closeConnection();
        }

        return cities;
    }
}
