package com.example.midtermapp.fragments_admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.midtermapp.AdminActivity;
import com.example.midtermapp.R;
import com.example.midtermapp.model.book;

import java.util.ArrayList;

public class Available_books extends Fragment {

    public Available_books() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayList<book> av_books = new ArrayList<>();

        AdminActivity.allBooks = av_books;
        View rootView = inflater.inflate(R.layout.profile_admin, container, false);

        return rootView;
    }

}
