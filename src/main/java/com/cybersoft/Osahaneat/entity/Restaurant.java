package com.cybersoft.Osahaneat.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "subtitle")
    private String subtitle;
    @Column(name = "description")
    private String desc;
    @Column(name = "image")
    private String image;
    @Column(name = "is_freeship")
    private Boolean isFreeship;
    @Column(name = "address")
    private String address;
    @Column(name = "open_date")
    private Date openDate;

    @OneToMany(mappedBy = "restaurant")
    private Set<RatingRestaurant> lisRatingRestaurants;
    @OneToMany(mappedBy = "restaurant")
    private Set<Orders> listOrders;
    @OneToMany(mappedBy = "restaurant")
    private Set<MenuRestaurant> lisMenuRestaurants;

    @OneToMany(mappedBy = "restaurant")
    private Set<Promo> listPromos;

    public Set<Promo> getListPromos() {
        return listPromos;
    }

    public void setListPromos(Set<Promo> listPromos) {
        this.listPromos = listPromos;
    }

    public Set<MenuRestaurant> getLisMenuRestaurants() {
        return lisMenuRestaurants;
    }

    public void setLisMenuRestaurants(Set<MenuRestaurant> lisMenuRestaurants) {
        this.lisMenuRestaurants = lisMenuRestaurants;
    }

    public Set<Orders> getListOrders() {
        return listOrders;
    }

    public void setListOrders(Set<Orders> listOrders) {
        this.listOrders = listOrders;
    }

    public Set<RatingRestaurant> getLisRatingRestaurants() {
        return lisRatingRestaurants;
    }

    public void setLisRatingRestaurants(Set<RatingRestaurant> lisRatingRestaurants) {
        this.lisRatingRestaurants = lisRatingRestaurants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getFreeship() {
        return isFreeship;
    }

    public void setFreeship(Boolean freeship) {
        isFreeship = freeship;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
}
