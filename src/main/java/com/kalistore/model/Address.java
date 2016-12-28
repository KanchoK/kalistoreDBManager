package com.kalistore.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by kanch on 12/27/2016.
 */
@XmlRootElement
public class Address {
    private int addressId;
    private City city;
    private int zipCode;
    private String addressLine;
    private int mainAddress;

    @XmlElement(name = "id")
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public int getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(int mainAddress) {
        this.mainAddress = mainAddress;
    }
}
