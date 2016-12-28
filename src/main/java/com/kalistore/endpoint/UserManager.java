package com.kalistore.endpoint;

import com.google.gson.Gson;
import com.kalistore.dao.UserDao;
import com.kalistore.model.User;
import com.kalistore.utils.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.net.URI;
import java.sql.SQLException;

/**
 * Created by kanch on 12/9/2016.
 */
@Path("user")
public class UserManager {
    @Context
    private HttpServletRequest request;

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User user) {
        UserDao userDao = new UserDao();
        user.setPassword(SecurityUtils.getHashedPassword(user.getPassword()));
        userDao.addUser(user);
        return Response.status(HttpURLConnection.HTTP_OK).entity("User added.").build();
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(User user) throws SQLException {
        user.setPassword(SecurityUtils.getHashedPassword(user.getPassword()));
        UserDao userDao = new UserDao();
        int userId = userDao.validation(user);
        if (userId != -1) {
            return Response.status(HttpURLConnection.HTTP_OK).entity(userId).build();
        }
        return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
    }

    @GET
    @Path("info")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public User getUserInfo(@QueryParam("userId") int userId) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.getUserInfo(userId);
    }
}
