package com.example.newmandal.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.newmandal.Model.CommonModel;
import com.example.newmandal.Model.Debug;
import com.example.newmandal.R;
import com.example.newmandal.service.RestClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    FloatingActionButton login;
    PinView enterpin;
    TextView np;


//    new project updated


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        enterpin = findViewById(R.id.enterpin);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newpin = enterpin.getText().toString().trim();


                if (newpin.isEmpty() || newpin.length() < 4) {

                    Toast toast = Toast.makeText(LoginActivity.this, "Enter Pin", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    enterpin.requestFocus();

                } else {

                    pin(LoginActivity.this, newpin);

                }
            }
        });

//
//        if (code.isEmpty() || code.length() < 6) {
//
//            Toast toast = Toast.makeText(OtpSendActivity.this, "Enter Code", Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.CENTER, 0, 0);
//            toast.show();
//            enterpin.requestFocus();
//            return;
//        }
//





        //status colour
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.appcolor));
        }

    }



    private void pin(Context context, String newpin) {

        new RestClient(context).getInstance().get().login(newpin).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {

                Debug.e("Pin", new Gson().toJson(response.body()));



                if (response.body() != null) {

                    if (response.body().message.equals("Login successfull")) {

                        Intent intent = new Intent(LoginActivity.this, DesbordActivity.class);
                        startActivity(intent);
                        finish();




                        Toast.makeText(LoginActivity.this, "" + response.body().message, Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(LoginActivity.this, "" + response.body().message, Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(LoginActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "Somthing went  Wrong", Toast.LENGTH_SHORT).show();
                Debug.e("message", t.getLocalizedMessage());


            }
        });


    }

}
