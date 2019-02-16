package com.example.reuben.donatebloodkenya.models;

import java.util.List;

public class NewsResponse {

 private List<News> news;

    public NewsResponse(List<News> news) {
        this.news = news;
    }

    public List<News> getNews() {
        return news;
    }
}
