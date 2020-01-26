package com.example.moviebooking.data;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UserRepoImp implements UserRepoInterface {
    private final static DatabaseReference USER_REF = FirebaseDatabase.getInstance().getReference().child("Users");

    @Override
    public void updateUserData(UserModel userModel, String id, final Context context) {
        USER_REF.child(id).setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                    Toast.makeText(context, "your data has been successfully updated", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public MutableLiveData<UserModel> getUser(String id, final MutableLiveData<UserModel> userModelMutableLiveData) {
        USER_REF.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userModelMutableLiveData.setValue(dataSnapshot.getValue(UserModel.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return userModelMutableLiveData;
    }

    @Override
    public void uploadImage(Uri resultUri) {

        final StorageReference imageRef = FirebaseStorage.getInstance().getReference().child("pp");
        imageRef.child(FirebaseAuth.getInstance().getUid()).putFile(resultUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imageRef.child(FirebaseAuth.getInstance().getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.e("", uri + "");
                        String link = uri.toString();
                        USER_REF.child(FirebaseAuth.getInstance().getUid()).child("image").setValue(link);
                    }
                });


            }




});
    }
}
