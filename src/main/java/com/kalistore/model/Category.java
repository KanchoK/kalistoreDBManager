package com.kalistore.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by kanch on 12/12/2016.
 */
@XmlRootElement
public class Category implements Serializable {
    private int categoryId;
    private String name;
//    private List<Product> products;

    @XmlElement(name = "id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }


    @Override
    public int hashCode() {
        int hash = 9;
        hash = 17 * hash + (this.categoryId != 0 ? this.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean val = false;

        if (obj instanceof Category) {
            val = this.getCategoryId() == ((Category) obj).getCategoryId();
        }

        return val;
    }
}
