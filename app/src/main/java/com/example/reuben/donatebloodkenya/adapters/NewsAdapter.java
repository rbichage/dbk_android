package com.example.reuben.donatebloodkenya.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reuben.donatebloodkenya.R;
import com.example.reuben.donatebloodkenya.activities.NewsResultActivity;
import com.example.reuben.donatebloodkenya.models.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private List<News> newsList;

    public NewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    public void setNewsList(List<News> newsList) {

    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);

        holder.news_title.setText(news.getTitle());
        holder.newsTime.setText(news.getCreated_at());
        holder.newsImage.setImageDrawable(context.getResources().getDrawable(R.drawable.profile_header));




    }

    @Override
    public int getItemCount() {

        return newsList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView news_title, news_description, newsTime;
        ImageView newsImage;
        CardView newsCard;




        NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            newsCard = itemView.findViewById(R.id.news_card);
            newsCard.setOnClickListener(this);
            news_title = itemView.findViewById(R.id.news_title);
            newsImage = itemView.findViewById(R.id.news_photo);
            newsTime = itemView.findViewById(R.id.news_time);
        }

        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.news_card) {
                News news = newsList.get(getAdapterPosition());
                Intent intent = new Intent(context, NewsResultActivity.class);
                intent.putExtra("news", news);
                context.startActivity(intent);
            }


        }
    }
}
