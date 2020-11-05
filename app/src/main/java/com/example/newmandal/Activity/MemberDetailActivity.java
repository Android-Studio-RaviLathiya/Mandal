package com.example.newmandal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newmandal.Adepter.CreditAdapter;
import com.example.newmandal.Model.MemberDitailModel;
import com.example.newmandal.Model.MemberName;
import com.example.newmandal.R;
import com.example.newmandal.service.RestClient;
import com.marcoscg.dialogsheet.DialogSheet;
import com.xw.repo.widget.BounceScrollView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberDetailActivity extends AppCompatActivity {

    TextView member_name, mobile, memberCredit, memberDebit, memberInterest, membernumber, address;
    Button back, add_credit, next;
    RecyclerView rv;
    String member_id;
    CreditAdapter creditAdapter;
    ImageView call;
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_detail);


        back = findViewById(R.id.back);

        add_credit = findViewById(R.id.add_credit);
        memberDebit = findViewById(R.id.memberDebit);
        memberCredit = findViewById(R.id.memberCredit);
        memberInterest = findViewById(R.id.memberInterest);

        membernumber = findViewById(R.id.membernumber);
        address = findViewById(R.id.address);
        member_name = findViewById(R.id.member_name);
        mobile = findViewById(R.id.mobile);

        rv = findViewById(R.id.rv);


        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null) {
            member_id = bundle1.getString("member_id");

        }

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


        rv.setLayoutManager(new LinearLayoutManager(MemberDetailActivity.this));
        rv.setNestedScrollingEnabled(false);

        creditAdapter = new CreditAdapter(MemberDetailActivity.this);
        rv.setAdapter(creditAdapter);

        add_credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MemberDetailActivity.this, AddcreditActivity.class);
                intent.putExtra("member_id", member_id);
                startActivityForResult(intent, 426);


            }
        });

        getcredit(this, member_id);
        topmemberdetail(this, member_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 426) {
            getcredit(this, member_id);
            topmemberdetail(this, member_id);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void topmemberdetail(Context context, String member_id) {
        new RestClient(context).getInstance().get().gettopmemberdetail(member_id).enqueue(new Callback<MemberName>() {
            @Override
            public void onResponse(Call<MemberName> call, Response<MemberName> response) {


                if (response.body() != null) {


                    mobile.setText(response.body().mobile + "");
                    member_name.setText(response.body().name + "");
                    address.setText(response.body().address + "");
                    membernumber.setText(response.body().member_no + "");

                    memberCredit.setText(response.body().credit + "");
                    memberDebit.setText(response.body().debit + "");
                    memberInterest.setText(response.body().interest + "");

                } else {
                    Toast.makeText(MemberDetailActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<MemberName> call, Throwable t) {

                Toast.makeText(MemberDetailActivity.this, "Something Went Wrong...", Toast.LENGTH_SHORT).show();


            }
        });
    }


    private void getcredit(Context context, String member_id) {

        new RestClient(context).getInstance().get().getmembercreait(member_id).enqueue(new Callback<MemberDitailModel>() {
            @Override
            public void onResponse(Call<MemberDitailModel> call, Response<MemberDitailModel> response) {

                if (response.body() != null) {

                    if (response.body().credits_member != null) {


                        creditAdapter.addAll(response.body().credits_member);

                    } else {
                        Toast toast = Toast.makeText(MemberDetailActivity.this, "" + response.body().message, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                    }
                } else {
                    Toast.makeText(MemberDetailActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MemberDitailModel> call, Throwable t) {

                Toast.makeText(MemberDetailActivity.this, "Something Went Wrong...", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }

    public void call(View view) {


        if (ContextCompat.checkSelfPermission(MemberDetailActivity.this, Manifest.permission.CALL_PHONE) !=

                PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(MemberDetailActivity.this, new String[]
                    {Manifest.permission.CALL_PHONE}, REQUEST_CALL);

        } else {

            String dail = "tel:" + mobile.getText();
            MemberDetailActivity.this.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dail)));

        }
    }
}