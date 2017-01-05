package com.kalistore.endpoint;

import com.kalistore.dao.ReviewDao;
import com.kalistore.model.Review;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by kanch on 1/5/2017.
 */
@Path("review")
public class ReviewManager {
    @Context
    private HttpServletRequest request;

    @GET
    @Path("allForProduct")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<Review> getReviewsForProduct(@QueryParam("productId") int productId) throws SQLException {
        ReviewDao reviewDao = new ReviewDao();
        return reviewDao.getReviewsForProduct(productId);
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response createReview(Review review) throws SQLException {
        ReviewDao reviewDao = new ReviewDao();
        reviewDao.addReview(review);
        return Response.status(HttpURLConnection.HTTP_OK).build();
    }
}
