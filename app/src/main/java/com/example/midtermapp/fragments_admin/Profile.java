package com.example.midtermapp.fragments_admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.midtermapp.MainActivity;
import com.example.midtermapp.R;

import org.w3c.dom.Text;

public class Profile extends Fragment{
    TextView textView;
    TextView textViewM;
    public Profile() {
    }
    public void say(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_admin, container, false);

        textView = (TextView)rootView.findViewById(R.id.profile_namme);
        textViewM = (TextView)rootView.findViewById(R.id.mail);
        textView.setText(MainActivity.globalUser.getName() + " " + MainActivity.globalUser.getSurname());
        textViewM.setText(MainActivity.globalUser.getName()+"@example.com");
        return rootView;
    }
}