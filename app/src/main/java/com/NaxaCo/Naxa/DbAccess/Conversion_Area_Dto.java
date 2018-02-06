package com.NaxaCo.Naxa.DbAccess;

import java.sql.Blob;

/**
 * Created by cri_r on 05/02/2018.
 */

public class Conversion_Area_Dto {
    private int id;
    private String name;
    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
