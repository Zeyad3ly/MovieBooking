package com.example.moviebooking.ui;

import android.content.Context;

public interface LoginImp {
    void onVerify(String email, String password, final Context context);
    void sendUserToMain(Context context);
    void sendUserToSignUp();

}
