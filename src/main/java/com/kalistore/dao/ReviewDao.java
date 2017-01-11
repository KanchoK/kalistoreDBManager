package com.kalistore.dao;

import com.kalistore.model.Review;
import com.kalistore.model.User;
import com.kalistore.utils.DbConnection;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by kanch on 1/5/2017.
 */
public class ReviewDao {
    private Connection conn;

    public ReviewDao() {
        conn = DbConnection.getConnection();
    }

    public List<Review> getReviewsForProduct(int productId) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        ResultSet rs = null;

        try {
            if (conn.isClosed()) {
                conn = DbConnection.getConnection();
            }
            PreparedStatement preparedStatement = conn
                    .prepareStatement("SELECT reviewId, userId, text, rating, creationDate " +
                            "FROM reviews " +
                            "WHERE productId=?");
            preparedStatement.setInt(1, productId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Review review = new Review();
                review.setReviewId(rs.getInt("reviewId"));
                review.setText(rs.getString("text"));
                review.setRating(rs.getInt("rating"));
                review.setCreationDate(stringToDate(rs.getString("creationDate")));
                review.setUser(getUserInfo(rs.getInt("userId")));

                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            DbConnection.closeConnection();
        }

        return reviews;
    }

    public void addReview(Review review) {
        try {
            if (conn.isClosed()) {
                conn = DbConnection.getConnection();
            }

            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn
                    .prepareStatement("INSERT INTO reviews(productId, userId, text, rating, creationDate) " +
                            "VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, review.getProduct().getProductId());
            preparedStatement.setInt(2, review.getUser().getUserId());
            preparedStatement.setString(3, review.getText());
            preparedStatement.setInt(4, review.getRating());
            preparedStatement.setString(5, dateToString(review.getCreationDate()));
            preparedStatement.executeUpdate();

            updateProductRating(review.getProduct().getProductId(), review.getRating());

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeConnection();
        }
    }

    private Date stringToDate(String date) {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        Date parsedDate = null;
        try {
            parsedDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }

    private String dateToString(Date date) {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return format.format(date);
    }

    private User getUserInfo(int userId) throws SQLException {
        User user = new User();
        ResultSet rs = null;

        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("SELECT username, fullName " +
                            "FROM users " +
                            "WHERE userId=?");
            preparedStatement.setInt(1, userId);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setUserId(userId);
                user.setFullName(rs.getString("username"));
                user.setEmail(rs.getString("fullName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

        return user;
    }

    private void updateProductRating(int productId, int rating) throws SQLException {
        ResultSet rs = null;

        try {
            PreparedStatement countReviews = conn
                    .prepareStatement("SELECT COUNT(reviewId)as reviewsCount, SUM(rating) as ratingsSum FROM reviews WHERE productId=?;");
            countReviews.setInt(1, productId);
            rs = countReviews.executeQuery();
            if (rs.next() && rs.getInt("reviewsCount") > 0) {
                int reviewsCount = rs.getInt("reviewsCount");
                double ratingsSum = rs.getDouble("ratingsSum");

                PreparedStatement updateRating = conn
                        .prepareStatement("UPDATE products SET rating=? WHERE productId=?");
                updateRating.setDouble(1, ratingsSum / reviewsCount);
                updateRating.setInt(2, productId);
                updateRating.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
}
