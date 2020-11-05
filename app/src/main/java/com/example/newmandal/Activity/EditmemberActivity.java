package com.example.newmandal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newmandal.Model.CommonModel;
import com.example.newmandal.R;
import com.example.newmandal.service.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditmemberActivity extends AppCompatActivity {


    EditText mo, no, name, et_address;
    Button btn_add;
    String dmember_no, dnames, daddres, dmobiles, dmember_id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmember);

        no = findViewById(R.id.no);
        name = findViewById(R.id.name);
        mo = findViewById(R.id.mo);
        et_address = findViewById(R.id.address);
        btn_add = findViewById(R.id.btn_add);

        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null) {
            dmember_id = bundle1.getString("id");
            dmember_no = bundle1.getString("member_no");
            dnames = bundle1.getString("name");
            daddres = bundle1.getString("address");
            dmobiles = bundle1.getString("mobile");
        }


        /*back*/
        Button back;
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //status colour
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.appcolor));
        }


        no.setText(dmember_no);
        name.setText(dnames);
        et_address.setText(daddres);
        mo.setText(dmobiles);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ememberno = no.getText().toString();
                String ename = name.getText().toString();
                String eaddress = et_address.getText().toString();
                String emobile = mo.getText().toString();


                if (ememberno.equals("")){

                    no.setError("Enter Member No");

                }else if (ename.equals("")){

                    name.setError("Enter Name");
                }else if (eaddress.equals("")){

                    et_address.setError("Enter Adress");
                }else  if (emobile.equals("")){

                    mo.setError("Enter Mobile");

                }else {


                    editmembers(EditmemberActivity.this, dmember_id, ememberno,ename, eaddress, emobile);

                }
            }
        });


    }

    private void editmembers(EditmemberActivity editmemberActivity, String dmember_id, String ememberno, String ename, String eaddress, String emobile) {

           new RestClient(this).getInstance().get().geteditmember(dmember_id,ememberno,ename,eaddress,emobile).enqueue(new Callback<CommonModel>() {
               @Override
               public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {

                   if (response.body() != null) {

                    onBackPressed();

                    Toast.makeText(EditmemberActivity.this, "" + response.body().message, Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(EditmemberActivity.this, "Something Went Wrong..", Toast.LENGTH_SHORT).show();
                }
               }

               @Override
               public void onFailure(Call<CommonModel> call, Throwable t) {

                Toast.makeText(EditmemberActivity.this, "Something Went Wrong...", Toast.LENGTH_SHORT).show();

               }
           });



    }


    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }



}


