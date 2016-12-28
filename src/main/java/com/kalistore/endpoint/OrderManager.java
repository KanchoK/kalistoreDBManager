package com.kalistore.endpoint;

import com.kalistore.dao.OrderDao;
import com.kalistore.model.Order;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.sql.SQLException;

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
}
