package com.example.moviebooking.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserRepoImp implements UserRepoInterface {

    private Context context;
    private final static DatabaseReference USER_REF = FirebaseDatabase.getInstance().getReference().child("Users");
    private UserModel userModel = new UserModel();




    @Override
    public void updateUserData(UserModel userModel, String id) {
        USER_REF.child(id).setValue(userModel);
    }

    public MutableLiveData<UserModel> getUser(String id, final MutableLiveData<UserModel> userModelMutableLiveData) {
        USER_REF.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userModelMutableLiveData.setValue( dataSnapshot.getValue(UserModel.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return userModelMutableLiveData;
    }
}
