package com.example.moviebooking.data;

import android.se.omapi.Session;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SessionQueriesImp implements SessionQueriesInterfece {

    private static final DatabaseReference movieRef = FirebaseDatabase.getInstance().getReference().child("Movies");



    @Override
    public void setSession(final String movieName, final String movieId, SessionModel sessionModel) {

        movieRef.child(movieName).child("Sessions").setValue(sessionModel);
    }

    @Override
    public void getSession(final String movieName, String movieId, final MutableLiveData<SessionModel> mutableLiveData) {

        movieRef.child(movieName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild("Sessions")) {
                    SessionModel sessionModel = new SessionModel();
                    for (int i = 0; i < 8; i++) {
                        sessionModel.getSeats().add(false);

                    }
                    movieRef.child(movieName).child("Sessions").setValue(sessionModel);
                    mutableLiveData.setValue(sessionModel);
                }
                else {

                    movieRef.child(movieName).child("Sessions").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            mutableLiveData.setValue(dataSnapshot.getValue(SessionModel.class));

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
