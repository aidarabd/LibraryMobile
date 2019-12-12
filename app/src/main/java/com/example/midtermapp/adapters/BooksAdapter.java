package com.example.midtermapp.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.midtermapp.R;
import com.example.midtermapp.model.book;

import java.util.List;

/**
 * Created by Abhi on 23 Sep 2017 023.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.CustomViewHolder> {
    private List<book> books;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView movieName, year, genre, rating;

        public CustomViewHolder(View view) {
            super(view);
            movieName = (TextView) view.findViewById(R.id.bookName);
            genre = (TextView) view.findViewById(R.id.authorName);
            year = (TextView) view.findViewById(R.id.category);
            rating = (TextView) view.findViewById(R.id.status);
        }
    }

    public BooksAdapter(List<book> books) {
        this.books = books;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_raw_layout, parent, false);

        return new CustomViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        book book = books.get(position);
        holder.movieName.setText(book.getName());
        holder.genre.setText(book.getAuthor());
        holder.year.setText(String.valueOf(book.getCategory()));
        holder.rating.setText(String.valueOf(book.getStatus()));
    }
    @Override
    public int getItemCount() {
        return books.size();
    }
}