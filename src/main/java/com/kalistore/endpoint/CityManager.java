package com.kalistore.endpoint;

import com.kalistore.dao.CityDao;
import com.kalistore.dao.OfficeDao;
import com.kalistore.model.City;
import com.kalistore.model.Office;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by kanch on 12/27/2016.
 */
@Path("city")
public class CityManager {
    @Context
    private HttpServletRequest request;

    @GET
    @Path("allCities")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<City> getAllCities() throws SQLException {
        CityDao cityDao = new CityDao();
        return cityDao.getAllCities();
    }
}
