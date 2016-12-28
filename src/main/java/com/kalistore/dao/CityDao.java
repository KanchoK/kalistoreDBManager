package com.kalistore.dao;

import com.kalistore.model.City;
import com.kalistore.utils.DbConnection;

import java.sql.*;
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

    public static City findCityForId(int cityId, ResultSet rs, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT name " +
                        "FROM cities " +
                        "WHERE cityId=?");
        preparedStatement.setInt(1, cityId);
        rs = preparedStatement.executeQuery();
        City city = new City();
        while (rs.next()) {
            city.setCityId(cityId);
            city.setName(rs.getString("name"));
        }
        return city;
    }
}
