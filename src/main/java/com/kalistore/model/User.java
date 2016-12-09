package com.kalistore.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.rpc.holders.BooleanHolder;

/**
 * Created by kanch on 12/9/2016.
 */
@XmlRootElement
public class User {
    private String userId;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private Boolean isBlocked;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }
}
