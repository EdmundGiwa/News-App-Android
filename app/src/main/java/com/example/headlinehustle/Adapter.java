package com.example.headlinehustle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.headlinehustle.Model.Articles;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<Articles> articles;

    public Adapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Articles news_articles = articles.get(position);
    holder.news_title.setText(news_articles.getTitle());
    holder.news_source.setText(news_articles.getSource());
    holder.news_date.setText(news_articles.getPublishedAt());

    String imageUrl = news_articles.getUrlToImage();

        Picasso.with(context).load(imageUrl).into(holder.news_img);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView news_title, news_source, news_date;
        ImageView news_img;
        CardView news_holder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            news_title = itemView.findViewById(R.id.tvTitle);
            news_source = itemView.findViewById(R.id.tvSource);
            news_date = itemView.findViewById(R.id.tvDate);
            news_img = itemView.findViewById(R.id.newsImage);
            news_holder = itemView.findViewById(R.id.cardview);

        }
    }

}
