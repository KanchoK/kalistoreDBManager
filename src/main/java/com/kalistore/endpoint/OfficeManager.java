package com.kalistore.endpoint;

import com.kalistore.dao.OfficeDao;
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
@Path("office")
public class OfficeManager {
    @Context
    private HttpServletRequest request;

    @GET
    @Path("allOffices")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<Office> getAllOffices() throws SQLException {
        OfficeDao officeDao = new OfficeDao();
        return officeDao.getAllOffices();
    }
}
