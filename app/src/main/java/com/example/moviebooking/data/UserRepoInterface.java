package com.example.moviebooking.data;

import android.content.Context;
import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

public interface UserRepoInterface {

    void updateUserData(UserModel userModel, String id , Context context);
    MutableLiveData<UserModel>  getUser (String id , MutableLiveData<UserModel> userModel);
    void uploadImage( Uri resultUri);

}
