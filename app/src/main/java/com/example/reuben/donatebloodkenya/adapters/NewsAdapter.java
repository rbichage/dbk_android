package com.example.reuben.donatebloodkenya.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reuben.donatebloodkenya.R;
import com.example.reuben.donatebloodkenya.models.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private List<News> newsList;

    public NewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }



    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_list, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);

        holder.news_title.setText(news.getTitle());
        holder.news_description.setText(news.getDescription());



    }

    @Override
    public int getItemCount() {

        return newsList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView news_title, news_description;



        NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            news_title = itemView.findViewById(R.id.news_title);
            news_description = itemView.findViewById(R.id.news_description);
        }
    }
}
