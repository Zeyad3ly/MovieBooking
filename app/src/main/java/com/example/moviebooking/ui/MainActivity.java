package com.example.moviebooking.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviebooking.R;
import com.example.moviebooking.data.MovieModel;
import com.example.moviebooking.viewModel.MainViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.imgOpenProfile)
    CircleImageView imgOpenProfile;
    @BindView(R.id.selectDetails)
    TextView selectDetails;
    @BindView(R.id.imgClear)
    ImageView imgClear;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.imgBg)
    ImageView imgBg;
    @BindView(R.id.cinemaNameSample)
    EditText cinemaNameSample;
    @BindView(R.id.imgSearch)
    ImageView imgSearch;
    @BindView(R.id.cardViewSearch)
    CardView cardViewSearch;
    @BindView(R.id.tvSelectMovie)
    TextView tvSelectMovie;
    @BindView(R.id.main_recyclerView)
    RecyclerView mainRecyclerView;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.btnProceed)
    Button btnProceed;
    MainViewModel mainViewModel;
    MainRecyclerViewAdapter recyclerViewAdapter;
    ArrayList<MovieModel> movieModels ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FirebaseApp.initializeApp(this);
        movieModels = new ArrayList<>();

        ImageView imgOpenProfile = findViewById(R.id.imgOpenProfile);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mainViewModel.getMutableLiveData().observe(this, new Observer<ArrayList<MovieModel>>() {
            @Override
            public void onChanged(ArrayList<MovieModel> movieModelss) {
                recyclerViewAdapter = new MainRecyclerViewAdapter(movieModelss, getApplicationContext());
                recyclerViewAdapter.notifyDataSetChanged();
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                mainRecyclerView.setLayoutManager(layoutManager);
                mainRecyclerView.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();
               if(movieModelss.size()>0) Log.e("MainActivityLog",movieModelss.get(0).getName());
            }
        });
        imgOpenProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            Intent i = new Intent(this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
    }

}
