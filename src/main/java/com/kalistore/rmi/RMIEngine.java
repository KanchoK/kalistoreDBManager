package com.kalistore.rmi;

import com.kalistore.dao.CatalogDao;
import com.kalistore.model.Product;
import com.kalistore.model.User;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by kanch on 1/7/2017.
 */
public class RMIEngine {

    private Stub stub;

    private static final String HOST = "192.168.0.102";

    public RMIEngine() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(HOST, 8123);
        stub = (Stub) registry.lookup("RecommendationEngine");
    }
    public List<Product> getSuggestionsForProduct(Product product) throws SQLException, RemoteException {
        CatalogDao catalogDao = new CatalogDao();
        return stub.getSuggestionsForProduct(catalogDao.getAllProducts(),product);
    }
    public List<Product> getSuggestionForOrders(User user) throws RemoteException {
        return stub.getSuggestionsForUser(user.getOrders());
    }
    public List<Product> getSuggestionsByRating() throws SQLException, RemoteException {
        CatalogDao catalogDao = new CatalogDao();
        return stub.getSuggestionsByRating(catalogDao.getAllProducts());
    }
}
