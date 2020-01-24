package com.example.moviebooking.viewModel;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviebooking.data.UserModel;
import com.example.moviebooking.ui.ActivitySignup;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SignUpViewModel extends ViewModel {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser;

    MutableLiveData<FirebaseUser> mutableLiveData = new MutableLiveData<>();

    private FirebaseUser verifyUser(final UserModel user, final ActivitySignup activitySignup) {

        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(activitySignup, " signUpCompleted", Toast.LENGTH_SHORT).show();
                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    user.setId(FirebaseAuth.getInstance().getUid());
                    uploadUserDataToFireBase(user);
                } else {

                    Toast.makeText(activitySignup, "ERRORRRR", Toast.LENGTH_SHORT).show();
                    firebaseUser = null;
                }


            }
        });


        return firebaseUser;
    }

    private void uploadUserDataToFireBase(UserModel user) {

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getId());
        userRef.setValue(user);

    }

    public MutableLiveData<FirebaseUser> getUser(UserModel user, ActivitySignup activitySignup) {

        mutableLiveData.setValue(verifyUser(user, activitySignup));
        return mutableLiveData;
    }
}
