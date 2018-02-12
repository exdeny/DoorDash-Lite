package com.dgoliy.doordashlite.data.remote.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dgoliy on 2/11/18.
 */

public class Restaurant {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("cover_img_url")
    private String coverUrl;

    @SerializedName("status")
    private String status;

    @SerializedName("delivery_fee")
    private int deliveryFee;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getStatus() {
        return status;
    }

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
}
