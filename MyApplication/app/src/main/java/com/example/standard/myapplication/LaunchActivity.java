package com.example.standard.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LaunchActivity extends AppCompatActivity {

    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.logo) ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.activity_launch);

        ButterKnife.bind(this);
        Picasso.get().load(R.drawable.aib_logo).into(ivLogo);
        Loading loading = new Loading();
        loading.execute();
    }

    private class Loading extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(5000);
                Log.d("Result","Not Good");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "You made it";
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            LaunchActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        }
    }
}
