package com.example.moviebooking.ui;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.view.View;
import com.example.moviebooking.R;
import com.example.moviebooking.data.UserModel;
import com.example.moviebooking.viewModel.ProfileViewModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnEditProfile)
    ImageView btnEditProfile;
    @BindView(R.id.topbar)
    LinearLayout topbar;
    @BindView(R.id.imgProfilePic)
    ImageView imgProfilePic;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    ProfileViewModel profileViewModel;
    @BindView(R.id.user_name_tv)
    TextView userNameTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        profileViewModel.getMutableLiveData(this).observe(this, new Observer<UserModel>() {
            @Override
            public void onChanged(UserModel userModel) {
            userNameTv.setText(userModel.getName());
            if(userModel.getImage()!=null) Picasso.get().load(userModel.getImage()).into(imgProfilePic);
            }
        });
        ImageView btnEditProfile = findViewById(R.id.btnEditProfile);
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EditProfile.class);
                startActivity(intent);
            }
        });
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent2);
            }
        });

    }
}
