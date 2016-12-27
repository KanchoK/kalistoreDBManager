package com.kalistore.endpoint;

import com.kalistore.dao.CatalogDao;
import com.kalistore.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
}
