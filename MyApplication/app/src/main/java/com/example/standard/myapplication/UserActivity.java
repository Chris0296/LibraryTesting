package com.example.standard.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity {

    @BindView(R.id.content) TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        User user = Parcels.unwrap(getIntent().getBundleExtra("Bundled Parcel").getParcelable("user"));
        //tvContent.setText("Name is " + user.getName() + ", age is " + user.getAge() + " years old. He is from " + user.getLocation());
    }
}
