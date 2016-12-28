package com.kalistore.dao;

import com.kalistore.model.Office;
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
public class OfficeDao {
    private Connection conn;

    public OfficeDao() {
        conn = DbConnection.getConnection();
    }

    public List<Office> getAllOffices() throws SQLException {
        List<Office> offices = new ArrayList<Office>();
        ResultSet rs = null;

        try {
            if (conn.isClosed()) {
                conn = DbConnection.getConnection();
            }
            Statement statement = conn.createStatement();
            rs = statement.executeQuery("SELECT officeId, name, cityId FROM offices");
            while (rs.next()) {
                Office office = new Office();
                office.setOfficeId(rs.getInt("officeId"));
                office.setName(rs.getString("name"));
                office.setCity(CityDao.findCityForId(rs.getInt("cityId"), rs, conn));

                offices.add(office);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            DbConnection.closeConnection();
        }

        return offices;
    }
}
