package com.example.newmandal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.newmandal.Adepter.IntrestAdepter;
import com.example.newmandal.Model.IntresetShow;
import com.example.newmandal.R;
import com.example.newmandal.service.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntrestListActivity extends AppCompatActivity {

    RecyclerView rv;
    IntrestAdepter intrestAdepter;
    String member_id;
    Button btn_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intrest_list);

        rv = findViewById(R.id.rv);
        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(IntrestListActivity.this, EditIntrestActivity.class);
                intent.putExtra("member_id", member_id);
                startActivityForResult(intent, 425);



            }
        });


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




        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null) {
            member_id = bundle1.getString("member_id");

        }

        rv.setLayoutManager(new LinearLayoutManager(IntrestListActivity.this));
        rv.setNestedScrollingEnabled(false);

        intrestAdepter = new IntrestAdepter(IntrestListActivity.this);
        rv.setAdapter(intrestAdepter);

        getIntrest(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 425) {
            if (resultCode == RESULT_OK) {
                getIntrest(this);
            }
        }
    }
    private void getIntrest(IntrestListActivity intrestListActivity) {


        new RestClient(this).getInstance().get().getintrestshow().enqueue(new Callback<IntresetShow>() {
            @Override
            public void onResponse(Call<IntresetShow> call, Response<IntresetShow> response) {

                if (response.body() != null){

                    if (response.body().interest_rates != null){

                        intrestAdepter.addAll(response.body().interest_rates);

                    }else {

                        Toast.makeText(intrestListActivity, ""+response.body().interest_rates, Toast.LENGTH_SHORT).show();
                    }


                }else {

                    Toast.makeText(intrestListActivity, "Something Went Wrong..", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<IntresetShow> call, Throwable t) {
                Toast.makeText(intrestListActivity, "Something Went Wrong...", Toast.LENGTH_SHORT).show();

            }
        });

    }
    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }

}