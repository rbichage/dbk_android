package com.example.reuben.donatebloodkenya.models;

import java.io.Serializable;

public class News implements Serializable {
    private Integer id;
    private String title, description, image_url, created_at;

    public News(Integer id, String title, String description, String image_url, String created_at) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image_url = image_url;
        this.created_at = created_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
