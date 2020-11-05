package com.example.newmandal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newmandal.Adepter.InstollmentsAdapter;
import com.example.newmandal.Model.InstollmentModal;
import com.example.newmandal.R;
import com.example.newmandal.service.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowInstolmentActivity extends AppCompatActivity {

    Button add_instolment;
    RecyclerView rv;
    String member_id,credit_id;
    InstollmentsAdapter instollmentAdapter;
    String extra;
    String interest_amounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_instolment);

        add_instolment = findViewById(R.id.add_instolment);
        rv = findViewById(R.id.rv);

        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null) {
            member_id = bundle1.getString("memberid");
            credit_id = bundle1.getString("creditid");
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

        instollmentlist(ShowInstolmentActivity.this,credit_id);


        add_instolment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ShowInstolmentActivity.this, AddInstollmentActivity.class);
                intent.putExtra("memberid", member_id);
                intent.putExtra("creditid", credit_id);
                intent.putExtra("total_credit", extra);
                intent.putExtra("interest_amountss", interest_amounts);
                startActivityForResult(intent, 25);


            }
        });


        rv.setLayoutManager(new LinearLayoutManager(ShowInstolmentActivity.this));
        rv.setNestedScrollingEnabled(false);

        instollmentAdapter = new InstollmentsAdapter(ShowInstolmentActivity.this);
        rv.setAdapter(instollmentAdapter);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 25) {
            if (resultCode == RESULT_OK) {
                instollmentlist(this,credit_id);
            }
        }
    }


    private void instollmentlist(ShowInstolmentActivity showInstolmentActivity, String credit_id) {

        new RestClient(this).getInstance().get().getinstollment(credit_id).enqueue(new Callback<InstollmentModal>() {
            @Override
            public void onResponse(Call<InstollmentModal> call, Response<InstollmentModal> response) {

                if (response.body() != null) {

                    if (response.body().installment != null) {

//                        ex.setText(response.body().remaining_credit);

//                        if (response.body().installment_remaining == "1")


                        if (response.body().installment_remaining.equals("0")) {

                            add_instolment.setVisibility(View.GONE);


                        }else if (response.body().installment_remaining.equals("1")){

                            add_instolment.setVisibility(View.VISIBLE);

                        }

                        instollmentAdapter.addAll(response.body().installment);

                        extra = response.body().remaining_credit;
                        interest_amounts = response.body().interest_amount;



                    } else {

                        Toast toast = Toast.makeText(ShowInstolmentActivity.this,""+ response.body().message,Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();


                    }
                } else {
                    Toast.makeText(ShowInstolmentActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
                }




            }

            @Override
            public void onFailure(Call<InstollmentModal> call, Throwable t) {

                Toast.makeText(showInstolmentActivity, "Something Went Wrong...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }

}
