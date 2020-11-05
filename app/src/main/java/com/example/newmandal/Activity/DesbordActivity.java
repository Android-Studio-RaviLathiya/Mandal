package com.example.newmandal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newmandal.Adepter.MemberAdeptare;
import com.example.newmandal.Model.CreditModel;
import com.example.newmandal.Model.MemberModel;
import com.example.newmandal.R;
import com.example.newmandal.service.RestClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DesbordActivity extends AppCompatActivity {

    TextView tv_debit, tv_credit, tv_ins;

    TextView tv_member;
    RecyclerView rv;
    FloatingActionButton btn_member;
    MemberAdeptare memberAdeptare;
    ImageView more;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desbord);


        btn_member = findViewById(R.id.btn_member);
        tv_member = findViewById(R.id.tv_member);
        tv_credit = findViewById(R.id.tv_credit);
        tv_debit = findViewById(R.id.tv_debit);
        tv_ins = findViewById(R.id.tv_ins);
        more = findViewById(R.id.more);


        rv = findViewById(R.id.rv);

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PopupMenu popupMenu = new PopupMenu(DesbordActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.more, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {


                        if (item.getItemId() == R.id.interest) {

                            startActivity(new Intent(DesbordActivity.this, IntrestListActivity.class));
                        }
                        return false;
                    }
                });


                popupMenu.show();


            }
        });





        //status colour
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.appcolor));
        }

        rv.setLayoutManager(new LinearLayoutManager(DesbordActivity.this));
        rv.setNestedScrollingEnabled(false);
        memberAdeptare = new MemberAdeptare(DesbordActivity.this);
        rv.setAdapter(memberAdeptare);

        btn_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DesbordActivity.this, AddMemberActivity.class);
                startActivityForResult(intent, 425);
            }
        });

        getmember(this);
        getcredit(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 425) {
            if (resultCode == RESULT_OK) {
                getmember(this);
                getcredit(this);

            }
        }
    }


    public void getmember(Context desbordActivity) {

        new RestClient(DesbordActivity.this).getInstance().get().getAllMember().enqueue(new Callback<MemberModel>() {
            @Override
            public void onResponse(Call<MemberModel> call, Response<MemberModel> response) {

                if (response.body() != null) {

                    if (response.body().member_list != null) {

                        tv_member.setText(response.body().total_member + " Member");

                        memberAdeptare.addAll(response.body().member_list);

//                        Log.e("ok", "" + response.body().total_member);
                    } else {
                        Toast.makeText(DesbordActivity.this, "" + response.body().member_list, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DesbordActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<MemberModel> call, Throwable t) {

                Toast.makeText(desbordActivity, "Something Went Wrong...", Toast.LENGTH_SHORT).show();

            }
        });
    }


    /*credit debit intrest call */

    public void getcredit(Context desbordActivity) {

        new RestClient(desbordActivity).getInstance().get().getSummery().enqueue(new Callback<CreditModel>() {
            @Override
            public void onResponse(Call<CreditModel> call, Response<CreditModel> response) {

                if (response.body() != null) {


                    tv_credit.setText(response.body().total_credit + "");
                    tv_debit.setText(response.body().total_debit + "");
                    tv_ins.setText(response.body().total_interest + "");

                } else {
                    Toast.makeText(DesbordActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<CreditModel> call, Throwable t) {

                Toast.makeText(DesbordActivity.this, "Something Went Wrong..", Toast.LENGTH_SHORT).show();

            }
        });
    }

}


//only call mate
//                    amount.setText(response.body().interest_rates.get(1).rate +"");
