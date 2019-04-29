package com.example.reuben.donatebloodkenya.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.reuben.donatebloodkenya.R;
import com.example.reuben.donatebloodkenya.models.News;

public class NewsResultActivity extends AppCompatActivity {
    private TextView tvTitle, tvDesescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_result);

        News news = (News) getIntent().getExtras().getSerializable("news");

        tvTitle = findViewById(R.id.news_title);
        tvTitle.setText(news.getTitle());

        tvDesescription = findViewById(R.id.news_content);
        tvDesescription.setText(news.getDescription());
        tvDesescription.setTextIsSelectable(true);
    }
}
