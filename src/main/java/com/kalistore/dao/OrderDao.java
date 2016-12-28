package com.kalistore.dao;

import com.kalistore.model.*;
import com.kalistore.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanch on 12/27/2016.
 */
public class OrderDao {
    private Connection conn;

    public OrderDao() {
        conn = DbConnection.getConnection();
    }

    public void createOrder(Order order) throws SQLException {
        ResultSet rs = null;

        try {
            if (conn.isClosed()) {
                conn = DbConnection.getConnection();
            }

            conn.setAutoCommit(false);

            User user = order.getUser();
            Delivery delivery = order.getDelivery();
            Client client = delivery.getClient();

            int clientId;

            PreparedStatement clientCheck = conn
                    .prepareStatement("SELECT clientId FROM clients " +
                            "WHERE userId=? AND fullName=? AND phone=?");
            clientCheck.setInt(1, user.getUserId());
            clientCheck.setString(2, client.getFullName());
            clientCheck.setString(3, client.getPhone());
            rs = clientCheck.executeQuery();
            if (rs.next()) {
                clientId = rs.getInt("clientId");
            } else {
                PreparedStatement createClient = conn
                        .prepareStatement("INSERT INTO clients(fullName, phone, userId) " +
                                "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                createClient.setString(1, client.getFullName());
                createClient.setString(2, client.getPhone());
                createClient.setInt(3, user.getUserId());
                createClient.executeUpdate();

                rs = createClient.getGeneratedKeys();
                rs.next();
                clientId = rs.getInt(1);
            }

            int deliveryAddressId;

            if (delivery.getIsToOffice() == 1) {
                deliveryAddressId = delivery.getOfficeId();
            } else {
                if (delivery.getDifferentAddress() == 1) {
                    PreparedStatement addressCheck = conn.prepareStatement("SELECT addressId FROM addresses " +
                            "WHERE cityId=? AND addressLine=? AND userId=? AND mainAddress!=1");
                    addressCheck.setInt(1, delivery.getAddress().getCity().getCityId());
                    addressCheck.setString(2, delivery.getAddress().getAddressLine());
                    addressCheck.setInt(3, user.getUserId());
                    rs = addressCheck.executeQuery();
                    if (rs.next()) {
                        deliveryAddressId = rs.getInt("addressId");
                    } else {
                        PreparedStatement createAddress = conn
                                .prepareStatement("INSERT INTO addresses(cityId, zipCode, addressLine, userId, mainAddress) " +
                                        "VALUES (?, ?, ?, ?, ?, 0)", Statement.RETURN_GENERATED_KEYS);
                        createAddress.setInt(1, delivery.getAddress().getCity().getCityId());
                        createAddress.setInt(2, delivery.getAddress().getZipCode());
                        createAddress.setString(3, delivery.getAddress().getAddressLine());
                        createAddress.setInt(4, user.getUserId());
                        createAddress.executeUpdate();

                        rs = createAddress.getGeneratedKeys();
                        rs.next();
                        deliveryAddressId = rs.getInt(1);
                    }
                } else {
                    PreparedStatement mainAddress = conn
                            .prepareStatement("SELECT addressId FROM addresses " +
                                    "WHERE userId=? AND mainAddress=1");
                    mainAddress.setInt(1, user.getUserId());
                    rs = mainAddress.executeQuery();
                    rs.next();
                    deliveryAddressId = rs.getInt("addressId");
                }
            }

            int deliveryId;

            PreparedStatement createDelivery = conn
                    .prepareStatement("INSERT INTO deliveries(deliveryAddressId, clientId, isToOffice, differentAddress) " +
                            "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            createDelivery.setInt(1, deliveryAddressId);
            createDelivery.setInt(2, clientId);
            createDelivery.setInt(3, delivery.getIsToOffice());
            createDelivery.setInt(4, delivery.getDifferentAddress());
            createDelivery.executeUpdate();

            rs = createDelivery.getGeneratedKeys();
            rs.next();
            deliveryId = rs.getInt(1);

            int orderId;

            PreparedStatement createOrder = conn
                    .prepareStatement("INSERT INTO orders(deliveryId, totalPrice, userId, status) VALUES (?, ?, ?, 1)",
                            Statement.RETURN_GENERATED_KEYS);
            createOrder.setInt(1, deliveryId);
            createOrder.setDouble(2, order.getTotalPrice());
            createOrder.setInt(3, user.getUserId());
            createOrder.executeUpdate();

            rs = createOrder.getGeneratedKeys();
            rs.next();
            orderId = rs.getInt(1);

            PreparedStatement orderEntries = conn
                    .prepareStatement("INSERT INTO orderentries(orderId, productId, quantity) VALUES (?, ?, ?)");
            orderEntries.setInt(1, orderId);
            for (OrderEntry orderEntry : order.getEntries()) {
                orderEntries.setInt(2, orderEntry.getProduct().getProductId());
                orderEntries.setInt(3, orderEntry.getQuantity());
                orderEntries.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            DbConnection.closeConnection();
        }
    }
}
