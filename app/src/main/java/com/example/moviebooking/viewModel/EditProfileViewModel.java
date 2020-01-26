package com.example.moviebooking.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviebooking.data.UserModel;
import com.example.moviebooking.data.UserRepoImp;

public class EditProfileViewModel extends ViewModel {


    private MutableLiveData<UserModel> userModel = new MutableLiveData<>();
    private UserRepoImp userRepoImp = new UserRepoImp();

    public MutableLiveData<UserModel> getUserModel(String id) {
       return userRepoImp.getUser(id,userModel);

    }

    public void setUserData(UserModel userModel, String id) {
        userRepoImp.updateUserData(userModel, id);
    }


}
