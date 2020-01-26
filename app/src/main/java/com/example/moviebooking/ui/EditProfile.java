package com.example.moviebooking.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.moviebooking.R;
import com.example.moviebooking.data.UserModel;
import com.example.moviebooking.viewModel.EditProfileViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity {
    @BindView(R.id.cover)
    ImageView cover;
    @BindView(R.id.profilePicture)
    CircleImageView profilePicture;
    @BindView(R.id.upload_pic_fab)
    FloatingActionButton uploadPicFab;
    @BindView(R.id.user_name_profile)
    TextView userNameProfile;
    @BindView(R.id.user_name_et)
    EditText userNameEt;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.email_address_tv)
    TextView emailAddressTv;
    @BindView(R.id.mail_address_et)
    EditText mailAddressEt;
    @BindView(R.id.update_data_button)
    Button updateDataButton;
    private EditProfileViewModel viewModel;
    private UserModel userModel;
    private Uri resultUri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (resultUri != null) Picasso.get().load(resultUri).into(profilePicture);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        userModel = new UserModel();
        viewModel = ViewModelProviders.of(this).get(EditProfileViewModel.class);
        viewModel.getUserModel(FirebaseAuth.getInstance().getUid()).observe(this, new Observer<UserModel>() {
            @Override
            public void onChanged(UserModel userModell) {
                userModel = userModell;
                if (userModel != null) {
                    if (userModel.getEmail() != null) mailAddressEt.setText(userModel.getEmail());
                    if (userModel.getImage() != null)
                        Picasso.get().load(userModel.getImage()).into(profilePicture);
                    if (userModel.getName() != null) userNameEt.setText(userModel.getName());
                    if (userModel.getPhone() != null) phoneEt.setText(userModel.getPhone());
                }
            }
        });
        updateDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = userNameEt.getText().toString();
                String phone = phoneEt.getText().toString();
                String email = mailAddressEt.getText().toString();
                if (resultUri != null) {
                    viewModel.uploadPic(resultUri);
                    Picasso.get().load(resultUri).into(profilePicture);


                }

                if (userName.isEmpty()) userNameEt.setError("you must add a name");
                else if (phone.isEmpty()) phoneEt.setError("you must add a phone number");
                else {
                    userModel.setEmail(email);
                    userModel.setName(userName);
                    userModel.setPhone(phone);
                    viewModel.setUserData(userModel, FirebaseAuth.getInstance().getUid(), getApplicationContext());
                }


            }
        });

        uploadPicFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(EditProfile.this);
            }
        });


    }
}
