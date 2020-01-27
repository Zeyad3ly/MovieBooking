package com.example.moviebooking.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviebooking.data.SessionModel;
import com.example.moviebooking.data.SessionQueriesImp;

public class SessionViewModel extends ViewModel {

    private MutableLiveData<SessionModel> mutableLiveData = new MutableLiveData<>();
    private SessionQueriesImp sessionQueriesImp = new SessionQueriesImp();

    public void setSession(String movieName, String movieId, SessionModel sessionModel) {
        sessionQueriesImp.setSession(movieName, movieId, sessionModel);

    }

    public MutableLiveData<SessionModel> getMutableLiveData(String movieName, String movieId) {
        sessionQueriesImp.getSession(movieName,movieId,mutableLiveData);
        return mutableLiveData;
    }
}
