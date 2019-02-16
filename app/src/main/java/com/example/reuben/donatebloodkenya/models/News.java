package com.example.reuben.donatebloodkenya.models;

public class News {
    private Integer id;
    private String title, description, updated_at, created_at;

    public News(Integer id, String title, String description, String updated_at, String created_at) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.updated_at = updated_at;
        this.created_at = created_at;

    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }
}
