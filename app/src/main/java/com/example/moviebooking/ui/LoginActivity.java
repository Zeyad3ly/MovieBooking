package com.example.moviebooking.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.moviebooking.R;
import com.example.moviebooking.viewModel.LoginViewModel;

import android.content.Context;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity implements LoginImp{

    LoginViewModel loginViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);




    }

    @Override
    public void onVerify(String email, String password, Context context) {

    }
}
