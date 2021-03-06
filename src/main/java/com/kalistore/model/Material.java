package com.kalistore.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by kanch on 12/12/2016.
 */
@XmlRootElement
public class Material implements Serializable {
    private int materialId;
    private String name;

    @XmlElement(name = "id")
    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
