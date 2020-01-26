package com.example.moviebooking.viewModel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviebooking.data.MovieModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    ArrayList<MovieModel> listOfMovieModels = new ArrayList<>();
    MutableLiveData<ArrayList<MovieModel>> mutableLiveData = new MutableLiveData<>();
    DatabaseReference movieRef = FirebaseDatabase.getInstance().getReference().child("Movies");



    public MutableLiveData<ArrayList<MovieModel>> getMutableLiveData() {

        movieRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    MovieModel movieModel = new MovieModel();
                    movieModel.setImage(data.child("image").getValue(String.class));
                    movieModel.setName(data.child("name").getValue(String.class));
                    movieModel.setId(data.child("id").getValue(String.class));
                    movieModel.setReleaseData(data.child("releaseData").getValue(String.class));

                   /* Log.e("MainViewModel", movieModel.getName());
                    Log.e("MainViewModel", movieModel.getImage());
                    Log.e("MainViewModel", movieModel.getId());
                    Log.e("MainViewModel", movieModel.getReleaseData());*/


                    listOfMovieModels.add(movieModel);
                }
                mutableLiveData.setValue(listOfMovieModels);
                Log.e("MainViewModel", listOfMovieModels.get(0).getName());
                //listOfMovieModels = dataSnapshot.getValue(ArrayList.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        Log.e("MainViewModel",  " " + listOfMovieModels.size());

       /* MovieModel movieModel = mutableLiveData.getValue().get(0);
        Log.e("MainViewModel", movieModel.getName());
        Log.e("MainViewModel", movieModel.getImage());
        Log.e("MainViewModel", movieModel.getId());
        Log.e("MainViewModel", movieModel.getReleaseData());*/
        return mutableLiveData;
    }
}
