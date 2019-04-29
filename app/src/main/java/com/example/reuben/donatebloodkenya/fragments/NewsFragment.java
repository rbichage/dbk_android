package com.example.reuben.donatebloodkenya.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reuben.donatebloodkenya.R;
import com.example.reuben.donatebloodkenya.adapters.NewsAdapter;
import com.example.reuben.donatebloodkenya.api.RetrofitClient;
import com.example.reuben.donatebloodkenya.models.News;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {

    RecyclerView recyclerView;
    private List<News> newsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.news_recycler);

        newsList = new ArrayList<>();

        getNews();

    }

    private void getNews() {

        Call<List<News>> call = RetrofitClient.getInstance().getApi().getNews();

        call.enqueue(new Callback<List<News>>() {

            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    newsList = response.body();
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    NewsAdapter newsAdapter = new NewsAdapter(getContext(), newsList);
                    recyclerView.setAdapter(newsAdapter);
                    newsAdapter.setNewsList(newsList);
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });
    }
}
