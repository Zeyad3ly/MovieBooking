package com.example.moviebooking.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.moviebooking.R;
import com.example.moviebooking.viewModel.LoginViewModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginImp {

    @BindView(R.id.imgBg)
    ImageView imgBg;
    @BindView(R.id.tvLogin)
    TextView tvLogin;
    @BindView(R.id.edtUserName)
    EditText edtUserName;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tvDontHaveAccount)
    TextView tvDontHaveAccount;
    private LoginViewModel loginViewModel;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        FirebaseApp.initializeApp(this);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                if (email.isEmpty())
                    Toast.makeText(LoginActivity.this, " add mail", Toast.LENGTH_SHORT).show();
                else if (password.isEmpty())
                    Toast.makeText(LoginActivity.this, "password", Toast.LENGTH_SHORT).show();
                else onVerify(email, password, getApplicationContext());


            }
        });

        tvDontHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToSignUp();
            }
        });
    }

    @Override
    public void onVerify(String email, String password, final Context context) {


        loginViewModel.getMutableLiveData(email, password, context).observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUserr) {
                firebaseUser = firebaseUserr;

                if (firebaseUser != null) {
                    // تمم
                    sendUserToMain(context);

                }

            }
        });




    }

    @Override
    public void sendUserToMain(Context context) {

        Intent i = new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @Override
    public void sendUserToSignUp() {
        Intent i = new Intent(this, ActivitySignup.class);
        startActivity(i);


    }
}
