package com.kalistore.endpoint;

import com.kalistore.dao.OrderDao;
import com.kalistore.model.Office;
import com.kalistore.model.Order;
import com.kalistore.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by kanch on 12/27/2016.
 */
@Path("order")
public class OrderManager {
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(Order order) throws SQLException {
        OrderDao orderDao = new OrderDao();
        orderDao.createOrder(order);
        return Response.status(HttpURLConnection.HTTP_OK).entity("Order created.").build();
    }

    @GET
    @Path("allForUser")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> registerUser(@QueryParam("userId") int userId) throws SQLException {
        OrderDao orderDao = new OrderDao();
        return orderDao.findOrdersForUser(userId);
    }
}
