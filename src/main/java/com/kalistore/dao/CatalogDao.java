package com.kalistore.dao;

import com.kalistore.model.Category;
import com.kalistore.model.Material;
import com.kalistore.model.Product;
import com.kalistore.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanch on 12/12/2016.
 */
public class CatalogDao {
    private Connection conn;

    public CatalogDao() {
        conn = DbConnection.getConnection();
    }

    public Product findProductById(Integer productId) throws SQLException {
        Product product = new Product();
        ResultSet rs = null;

        try {
            if (conn.isClosed()) {
                conn = DbConnection.getConnection();
            }
            PreparedStatement preparedStatement = conn
                    .prepareStatement("SELECT productId, title, description, price, rating, size, image, daysToMake " +
                            "FROM products " +
                            "WHERE productId=?");
            preparedStatement.setInt(1, productId);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                product.setProductId(rs.getInt("productId"));
                product.setTitle(rs.getString("title"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setRating(rs.getDouble("rating"));
                product.setSize(rs.getString("size"));
                product.setImage(rs.getString("image"));
                product.setDaysToMake(rs.getInt("daysToMake"));
            }

            product.setCategories(getCategoriesForProduct(productId, rs));

            product.setMaterials(getMaterialsForProduct(productId, rs));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            DbConnection.closeConnection();
        }

        return product;
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<Product>();
        ResultSet rs = null;

        try {
            if (conn.isClosed()) {
                conn = DbConnection.getConnection();
            }
            Statement statement = conn.createStatement();
            rs = statement.executeQuery("SELECT productId, title, description, price, rating, size, image, daysToMake " +
                    "FROM products");
            while (rs.next()) {
                Product product = new Product();
                int productId = rs.getInt("productId");

                product.setProductId(productId);
                product.setTitle(rs.getString("title"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setRating(rs.getDouble("rating"));
                product.setSize(rs.getString("size"));
                product.setImage(rs.getString("image"));
                product.setDaysToMake(rs.getInt("daysToMake"));
                product.setCategories(getCategoriesForProduct(productId, rs));
                product.setMaterials(getMaterialsForProduct(productId, rs));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            DbConnection.closeConnection();
        }

        return products;
    }

    public List<Product> findProductsByQuery(String query) {
        List<Product> products = new ArrayList<Product>();
        return products;
    }

    public List<Product> findProductsByCategory(String category) {
        List<Product> products = new ArrayList<Product>();
        return products;
    }

    private List<Category> getCategoriesForProduct(int productId, ResultSet rs) throws SQLException {
        PreparedStatement preparedStatement = conn
                .prepareStatement("SELECT c.categoryId, c.name FROM categories c " +
                        "INNER JOIN products2categories p2c " +
                        "ON p2c.categoryId=c.categoryId " +
                        "WHERE p2c.productId=?");
        preparedStatement.setInt(1, productId);
        rs = preparedStatement.executeQuery();
        List<Category> categories = new ArrayList<Category>();
        while (rs.next()) {
            Category category = new Category();
            category.setCategoryId(rs.getInt("categoryId"));
            category.setName(rs.getString("name"));
            categories.add(category);
        }
        return categories;
    }

    private List<Material> getMaterialsForProduct(int productId, ResultSet rs) throws SQLException {
        PreparedStatement preparedStatement = conn
                .prepareStatement("SELECT m.materialId, m.name FROM materials m " +
                        "INNER JOIN products2materials p2m " +
                        "ON p2m.materialId=m.materialId " +
                        "WHERE p2m.productId=?");
        preparedStatement.setInt(1, productId);
        rs = preparedStatement.executeQuery();
        List<Material> materials = new ArrayList<Material>();
        while (rs.next()) {
            Material material = new Material();
            material.setMaterialId(rs.getInt("materialId"));
            material.setName(rs.getString("name"));
            materials.add(material);
        }
        return materials;
    }
}
