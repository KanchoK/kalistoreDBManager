package com.kalistore.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by kanch on 12/27/2016.
 */
@XmlRootElement
public class Delivery {
    private int deliveryId;
    private Address address;
    private Client client;
    private int isToOffice;
    private int officeId;
    private int differentAddress;

    @XmlElement(name = "id")
    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getIsToOffice() {
        return isToOffice;
    }

    public void setIsToOffice(int isToOffice) {
        this.isToOffice = isToOffice;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public int getDifferentAddress() {
        return differentAddress;
    }

    public void setDifferentAddress(int differentAddress) {
        this.differentAddress = differentAddress;
    }
}
