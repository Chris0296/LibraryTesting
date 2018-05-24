package com.example.standard.myapplication;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.State;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.name) EditText etName;
    @BindView(R.id.age) EditText etAge;
    @BindView(R.id.location) EditText etLocation;
    @BindView(R.id.buttonSubmit) Button btnSubmit;
    @BindView(R.id.buttonView) Button btnView;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.buttonSubmit,R.id.buttonView})
    void onClick(View v) {
        switch(v.getId()) {
            case R.id.buttonSubmit:
                user = new User(etName.getText().toString(), etAge.getText().toString(), etLocation.getText().toString());
                Toast.makeText(getApplicationContext(), user.getName() + ", " + user.getAge() + ", " + user.getLocation(), Toast.LENGTH_LONG).show();
                break;
            case R.id.buttonView:
                Parcelable wrapped = Parcels.wrap(user);
                Bundle bundle = new Bundle();
                bundle.putParcelable("user", wrapped);
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("Bundled Parcel", bundle);
                startActivity(intent);

        }

    }

}
