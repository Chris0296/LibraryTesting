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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.State;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.name) EditText etName;
    @BindView(R.id.age) EditText etAge;
    @BindView(R.id.location) EditText etLocation;
    @BindView(R.id.buttonSubmit) Button btnSubmit;
    @BindView(R.id.buttonView) Button btnView;
    //User user;
    public Gson gson;
    private ArrayList<String> mEntries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        fetch(requestQueue);
    }

    private void fetch(RequestQueue requestQueue) {
        final JsonArrayRequest request = new JsonArrayRequest("https://jsonplaceholder.typicode.com/users/",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        ArrayList<User> userArrayList = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<User>>(){}.getType());
                        for(int i=0; i < userArrayList.size(); i++) {
                            Log.d("User String: ", userArrayList.get(i).getName() + " lives at " +
                                    userArrayList.get(i).getAddress().getStreet() + ". This is located at Latitude: " +
                                    userArrayList.get(i).getAddress().getGeo().getLat() + ", Longitude: " + userArrayList.get(i).getAddress().getGeo().getLng() +
                                    ". They work at: " + userArrayList.get(i).getCompany().getName());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity.this, "Unable to fetch data: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("ERROR IS: ", volleyError.getMessage());
                    }
                });

        requestQueue.add(request);
    }


    /*@OnClick({R.id.buttonSubmit,R.id.buttonView})
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

    }*/

}
