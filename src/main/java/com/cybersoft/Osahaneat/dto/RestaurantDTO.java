package com.cybersoft.Osahaneat.dto;

import java.util.Date;
import java.util.List;

public class RestaurantDTO {
    private String image;
    private String title;
    private double rating;
    private String subtitle;
    private boolean isFreeship;
    private Date openDate;
    List<CategoryDTO> categorires;

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public List<CategoryDTO> getCategorires() {
        return categorires;
    }

    public void setCategorires(List<CategoryDTO> categorires) {
        this.categorires = categorires;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public boolean isFreeship() {
        return isFreeship;
    }

    public void setFreeship(boolean freeship) {
        isFreeship = freeship;
    }
}
