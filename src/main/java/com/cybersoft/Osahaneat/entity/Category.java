package com.cybersoft.Osahaneat.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_cate")
    private String nameCate;
    @Column(name = "created_date")
    private Date createdDate;

    @OneToMany(mappedBy = "category")
    private Set<Food> lisFoods;
    @OneToMany(mappedBy = "category")
    private Set<MenuRestaurant> lisMenuRestaurants;

    public Set<MenuRestaurant> getLisMenuRestaurants() {
        return lisMenuRestaurants;
    }

    public void setLisMenuRestaurants(Set<MenuRestaurant> lisMenuRestaurants) {
        this.lisMenuRestaurants = lisMenuRestaurants;
    }

    public Set<Food> getLisFoods() {
        return lisFoods;
    }

    public void setLisFoods(Set<Food> lisFoods) {
        this.lisFoods = lisFoods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
