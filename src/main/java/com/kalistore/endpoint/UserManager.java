package com.kalistore.endpoint;

import com.google.gson.Gson;
import com.kalistore.dao.UserDao;
import com.kalistore.model.User;
import com.kalistore.utils.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.net.URI;

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
    public Response login(User user) {
        return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
    }

    @POST
    @Path("logout")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logout() {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return Response.seeOther(URI.create("http://localhost:8080/")).build();
    }


}
