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
import com.example.moviebooking.data.UserModel;
import com.example.moviebooking.viewModel.SignUpViewModel;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivitySignup extends AppCompatActivity implements SignUpImp {

    @BindView(R.id.imgBg)
    ImageView imgBg;
    @BindView(R.id.tvSignUp)
    TextView tvSignUp;
    @BindView(R.id.edtUserName)
    EditText edtUserName;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtConfirmPassword)
    EditText edtConfirmPassword;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;

    SignUpViewModel signUpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignUp();
            }
        });

    }

    @Override
    public void onSignUp() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String passwordRe = edtConfirmPassword.getText().toString();
        String userName = edtUserName.getText().toString();

        if (email.isEmpty()) Toast.makeText(this, "add email", Toast.LENGTH_SHORT).show();
        else if (userName.isEmpty())
            Toast.makeText(this, "add userName", Toast.LENGTH_SHORT).show();
        else if (password.isEmpty())
            Toast.makeText(this, "you must add password", Toast.LENGTH_SHORT).show();
        else if (passwordRe.isEmpty())
            Toast.makeText(this, "you must confirm your password", Toast.LENGTH_SHORT).show();
        else if (!password.equals(passwordRe))
            Toast.makeText(this, "your password doesn't match", Toast.LENGTH_SHORT).show();
        else {

            UserModel user = new UserModel(userName, password, email);
            signUpViewModel.getUser(user,this).observe(this, new Observer<FirebaseUser>() {
                @Override
                public void onChanged(FirebaseUser firebaseUser) {
                    if (firebaseUser != null)
                        sendUserToMain(getApplicationContext());

                }
            });

        }

    }

    @Override
    public void sendUserToMain(Context context) {

        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

    }
}
