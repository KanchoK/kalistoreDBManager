package com.kalistore.endpoint;

import com.kalistore.dao.CatalogDao;
import com.kalistore.model.Product;
import com.kalistore.rmi.RMIEngine;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by kanch on 12/12/2016.
 */
@Path("catalog")
public class CatalogManager {
    @Context
    private HttpServletRequest request;

    @GET
    @Path("productById")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Product getProductById(@QueryParam("productId") int productId) throws SQLException {
        CatalogDao catalogDao = new CatalogDao();
        return catalogDao.findProductById(productId);
    }

    @GET
    @Path("productsByIds")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<Product> getProductsByQuery(@QueryParam("productIds") List<Integer> productIds) throws SQLException {
        CatalogDao catalogDao = new CatalogDao();
        return catalogDao.getProductsByIds(productIds);
    }

    @GET
    @Path("allProducts")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<Product> getAllProducts() throws SQLException {
        CatalogDao catalogDao = new CatalogDao();
        return catalogDao.getAllProducts();
    }

    @GET
    @Path("productsByQuery")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<Product> getProductsByQuery(@QueryParam("query") String query) throws SQLException {
        CatalogDao catalogDao = new CatalogDao();
        return catalogDao.findProductsByQuery(query);
    }

    @GET
    @Path("productsByCategory")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<Product> getProductsByQuery(@QueryParam("categoryId") int categoryId) throws SQLException {
        CatalogDao catalogDao = new CatalogDao();
        return catalogDao.findProductsByCategory(categoryId);
    }

    @GET
    @Path("productRecommendationsByRating")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<Product> getProductRecommendationsByRating() throws SQLException, RemoteException, NotBoundException {
        RMIEngine rmiEngine = new RMIEngine();
        return rmiEngine.getSuggestionsByRating();
    }

    @GET
    @Path("productRecommendationsByProduct")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<Product> getProductRecommendationsByProduct(@QueryParam("productId") int productId) throws SQLException, RemoteException, NotBoundException {
        CatalogDao catalogDao = new CatalogDao();
        Product product = catalogDao.findProductById(productId);
        RMIEngine rmiEngine = new RMIEngine();
        return rmiEngine.getSuggestionsForProduct(product);
    }
}
