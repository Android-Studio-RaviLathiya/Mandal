package com.example.newmandal.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newmandal.Model.CommonModel;
import com.example.newmandal.R;
import com.example.newmandal.service.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMemberActivity extends AppCompatActivity {

    EditText mo, no, name, address;
    Button btn_add;
    String member_id,names,addresss,mobiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);


        no = findViewById(R.id.no);
        name = findViewById(R.id.name);
        btn_add = findViewById(R.id.btn_add);
        mo = findViewById(R.id.mo);
        address = findViewById(R.id.address);

        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null) {
            member_id = bundle1.getString("name");
            names = bundle1.getString("name");
            addresss = bundle1.getString("address");
            mobiles = bundle1.getString("mobile");
        }


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = no.getText().toString();
                String fname = name.getText().toString();
                String mobile = mo.getText().toString();
                String add = address.getText().toString();

                if (number.equals("")) {
                    no.setError("Field Number");

                } else if (fname.equals("")) {
                    no.setError("Field Number");

                } else if (add.equals("")) {
                    no.setError("Field Address");

                } else if (mobile.equals("")) {
                    no.setError("Field  Mobile Number");

                } else if (mobile.length() < 10) {
                    no.setError("Not Valid Mobile Number");

                } else {

                    addmember(AddMemberActivity.this, number, fname, add, mobile);
                }
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

    }

    public void addmember(AddMemberActivity addMemberActivity, String member_no, String name, String address, String mobile) {

        new RestClient(AddMemberActivity.this).getInstance().get().addMember(member_no, name, address, mobile).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {

                if (response.body() != null){

                    onBackPressed();
                    Toast.makeText(AddMemberActivity.this, ""+response.body().message, Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(AddMemberActivity.this, "Something Went Wrong..", Toast.LENGTH_SHORT).show();
                }
                
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {

                Toast.makeText(AddMemberActivity.this, "Something Went Wrong...", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }
}
