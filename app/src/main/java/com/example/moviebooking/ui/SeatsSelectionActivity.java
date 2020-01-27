package com.example.moviebooking.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.moviebooking.R;
import com.example.moviebooking.data.SessionModel;
import com.example.moviebooking.viewModel.MainViewModel;
import com.example.moviebooking.viewModel.SessionViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SeatsSelectionActivity extends AppCompatActivity {

    @BindView(R.id.imgOpenProfile)
    CircleImageView imgOpenProfile;
    @BindView(R.id.movieName)
    TextView movieNameTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout_seats)
    AppBarLayout appBarLayoutSeats;
    @BindView(R.id.mainScreen)
    ImageView mainScreen;
    @BindView(R.id.movie_poster)
    ImageView moviePoster;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.seat1)
    Button seat1;
    @BindView(R.id.seat2)
    Button seat2;
    @BindView(R.id.seat3)
    Button seat3;
    @BindView(R.id.seat4)
    Button seat4;
    @BindView(R.id.seat8)
    Button seat8;
    @BindView(R.id.seat7)
    Button seat7;
    @BindView(R.id.seat6)
    Button seat6;
    @BindView(R.id.seat5)
    Button seat5;
    @BindView(R.id.btnProceed)
    Button btnProceed;
    SessionModel mainSessionModel;

    private SessionViewModel viewModel;
    private MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seats_selection);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(SessionViewModel.class);
        final String movieName = getIntent().getStringExtra("movieName");
        final String movieId = getIntent().getStringExtra("movieId");
        String image = getIntent().getStringExtra("moviePic");
        Picasso.get().load(image).into(moviePoster);
        movieNameTv.setText(movieName);


        mainSessionModel = new SessionModel();
        viewModel.getMutableLiveData(movieName, movieId).observe(this, new Observer<SessionModel>() {
            @Override
            public void onChanged(SessionModel sessionModel) {
                handleButtons(sessionModel.getSeats());
                mainSessionModel = sessionModel;

            }
        });


        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setSession(movieName,movieId,mainSessionModel);


            }
        });

        seat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mainSessionModel.getSeats().get(0)) {
                    seat1.setBackgroundColor(Color.BLUE);
                    mainSessionModel.getSeats().set(0,true);
                }


            }
        });

        seat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mainSessionModel.getSeats().get(1)) {
                    seat2.setBackgroundColor(Color.BLUE);
                    mainSessionModel.getSeats().set(1,true);
                }


            }
        });


        seat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mainSessionModel.getSeats().get(2)) {
                    seat3.setBackgroundColor(Color.BLUE);
                    mainSessionModel.getSeats().set(2,true);
                }


            }
        });

        seat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mainSessionModel.getSeats().get(3)) {
                    seat4.setBackgroundColor(Color.BLUE);
                    mainSessionModel.getSeats().set(3,true);
                }


            }
        });


        seat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mainSessionModel.getSeats().get(4)) {
                    seat5.setBackgroundColor(Color.BLUE);
                    mainSessionModel.getSeats().set(4,true);
                }


            }
        });
        seat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mainSessionModel.getSeats().get(5)) {
                    seat6.setBackgroundColor(Color.BLUE);
                    mainSessionModel.getSeats().set(5,true);
                }


            }
        });


        seat7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mainSessionModel.getSeats().get(6)) {
                    seat7.setBackgroundColor(Color.BLUE);
                    mainSessionModel.getSeats().set(6,true);
                }


            }
        });


        seat8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mainSessionModel.getSeats().get(7)) {
                    seat8.setBackgroundColor(Color.BLUE);
                    mainSessionModel.getSeats().set(7,true);
                }


            }
        });







    }



    private void handleButtons(ArrayList<Boolean> seats) {

        if(seats.get(0)) seat1.setBackgroundColor(Color.RED);
        if(seats.get(1)) seat2.setBackgroundColor(Color.RED);
        if(seats.get(2)) seat3.setBackgroundColor(Color.RED);
        if(seats.get(3)) seat4.setBackgroundColor(Color.RED);
        if(seats.get(4)) seat5.setBackgroundColor(Color.RED);
        if(seats.get(5)) seat6.setBackgroundColor(Color.RED);
        if(seats.get(6)) seat7.setBackgroundColor(Color.RED);
        if(seats.get(7)) seat8.setBackgroundColor(Color.RED);

    }
}
