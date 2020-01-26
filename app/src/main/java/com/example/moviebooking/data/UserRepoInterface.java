package com.example.moviebooking.data;

import androidx.lifecycle.MutableLiveData;

public interface UserRepoInterface {

    void updateUserData(UserModel userModel, String id);
    MutableLiveData<UserModel>  getUser (String id , MutableLiveData<UserModel> userModel);

}
