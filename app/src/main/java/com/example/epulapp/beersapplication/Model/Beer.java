package com.example.epulapp.beersapplication.Model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by Epulapp on 05/12/2017.
 */

public class Beer {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("image_url")
    @Expose
    private String image_url;

    @SerializedName("abv")
    @Expose
    private double abv;

    @SerializedName("food_pairing")
    @Expose
    private String[] food_pairing;

    private Bitmap img = null;

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public String[] getFood_pairing() {
        return food_pairing;
    }

    public void setFood_pairing(String[] food_pairing) {
        this.food_pairing = food_pairing;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image_url='" + image_url + '\'' +
                ", abv=" + abv +
                ", food_pairing=" + Arrays.toString(food_pairing) +
                '}';
    }
}
