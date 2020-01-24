package com.example.moviebooking.viewModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviebooking.data.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileViewModel extends ViewModel {
    MutableLiveData<UserModel> mutableLiveData = new MutableLiveData<>();
    UserModel userModel;

    public MutableLiveData<UserModel> getMutableLiveData(final Context context) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getUid());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("ProfileViewModel" , dataSnapshot + "");

                userModel = dataSnapshot.getValue(UserModel.class);
                mutableLiveData.setValue(userModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(context, "couldn't retrive data", Toast.LENGTH_SHORT).show();
            }
        });


        return mutableLiveData;
    }
}
