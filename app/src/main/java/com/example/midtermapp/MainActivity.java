package com.example.midtermapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.midtermapp.db.DatabaseHelper;
import com.example.midtermapp.model.user;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    public static user globalUser;
    public TextView mTextViewRegister;
    ArrayList<user> users = new ArrayList<>();
    ViewGroup progressView;
    DatabaseHelper db;
    protected boolean isProgressShowing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Name = (EditText) findViewById(R.id.edittext_username);
        Password = (EditText) findViewById(R.id.edittext_password);
        Info = (TextView) findViewById(R.id.etInfo);
        Login = (Button) findViewById(R.id.button_login);
        db = new DatabaseHelper(this);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setGlobe();
                String user = Name.getText().toString();
                String pwd = Password.getText().toString();
                boolean res = db.checkUser(user, pwd);
                if(res)
                {
                    user u = new user();
                    u.setName(user);
                    u.setSurname(user+"ov(a)");
                    globalUser =u;
                    Intent HomePage = new Intent(MainActivity.this,AdminActivity.class);
                    startActivity(HomePage);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mTextViewRegister = (TextView)findViewById(R.id.textview_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }
    private void validate(String userEnteredName, String userEnteredPassword){
        for(user u: users){
            if(u.getName().equals(userEnteredName) && u.getPassword().equals(userEnteredPassword)){
                Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(intent);
                globalUser = u;
            }
            else{
                counter--;
                Info.setText("number of attempts left"+counter);
            }
        }

    }

    public void setGlobe(){
        globalUser.setName(Name.getText().toString());
        globalUser.setSurname(Name.getText().toString()+"ov(a)");
    }
    public void showProgressingView() {

        if (!isProgressShowing) {
            View view=findViewById(R.id.progressBar1);
            view.bringToFront();
        }
    }
    public void hideProgressingView() {
        View v = this.findViewById(android.R.id.content).getRootView();
        ViewGroup viewGroup = (ViewGroup) v;
        viewGroup.removeView(progressView);
        isProgressShowing = false;
    }
}
