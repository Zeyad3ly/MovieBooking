package com.example.moviebooking.viewModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LoginViewModel extends ViewModel {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser user;
    private MutableLiveData<FirebaseUser> mutableLiveData;


    public FirebaseUser getUserFromDataBase(String email, String password, final Context context) {


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithEmail:success");
                            user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            user = null;
                        }

                    }
                });

        return user;

    }

    public MutableLiveData<FirebaseUser> getMutableLiveData(String email, String password, final Context context) {
        mutableLiveData.setValue(getUserFromDataBase(email, password, context));

        return mutableLiveData;
    }
}
