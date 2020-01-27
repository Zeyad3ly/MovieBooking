package com.example.moviebooking.data;

import androidx.lifecycle.MutableLiveData;

public interface SessionQueriesInterfece {
    void setSession(String movieName , String movieId , SessionModel sessionModel);
    void getSession(String movieName , String movieId,MutableLiveData<SessionModel> mutableLiveData);

}
