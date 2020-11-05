package com.example.newmandal.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.newmandal.Model.Debug;
import com.example.newmandal.Model.MemberModel;
import com.example.newmandal.R;
import com.example.newmandal.service.RestClient;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

//    new project

    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);

        new RestClient(this).getInstance().get().getAllMember().enqueue(new Callback<MemberModel>() {
            @Override
            public void onResponse(Call<MemberModel> call, Response<MemberModel> response) {

                Debug.e("call", new Gson().toJson(response.body()));
                text.setText(new Gson().toJson(response.body()));


            }

            @Override
            public void onFailure(Call<MemberModel> call, Throwable t) {
                Debug.e("call", t.getLocalizedMessage());
            }
        });


    }
}

