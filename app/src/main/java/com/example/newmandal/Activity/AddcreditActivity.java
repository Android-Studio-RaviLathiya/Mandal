package com.example.newmandal.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newmandal.Model.CommonModel;
import com.example.newmandal.MyPrefrence;
import com.example.newmandal.R;
import com.example.newmandal.service.RestClient;
import com.marcoscg.dialogsheet.DialogSheet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddcreditActivity extends AppCompatActivity {

    Button add;
    EditText amount;
    ImageView more;
    TextView setinterest;
    String rate_id, rate;
    String member_id;
    String amounts;
    TextView id;
    String member;
    LinearLayout click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcredit);

        add = findViewById(R.id.add);
        setinterest = findViewById(R.id.setinterest);
        amount = findViewById(R.id.amount);
        id = findViewById(R.id.id);
        click = findViewById(R.id.click);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddcreditActivity.this, IntrestListActivity.class);
                intent.putExtra("id", member_id);
                startActivity(intent);


                MyPrefrence.setamountvalue(true);
                if (MyPrefrence.getamountvalue()) {
                    MyPrefrence.setmmid(member_id);
                }

                finish();
            }
        });


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            rate_id = bundle.getString("id");
            rate = bundle.getString("rate");
        }
        setinterest.setText(rate);

        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null) {
            member_id = bundle1.getString("member_id");
        }
        id.setText(member_id);


        if (MyPrefrence.getamountvalue()) {
            id.setText(MyPrefrence.getmmid());
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
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.appcolor));
        }

        id.setText(MyPrefrence.getmmid());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amounts = amount.getText().toString();
                member = id.getText().toString();

                String interest = setinterest.getText().toString();


                if (amounts.equals("")) {

                    amount.setError("Enter Amount");

                } else if (interest.equals("")) {

                    Toast.makeText(AddcreditActivity.this, "Select Interest", Toast.LENGTH_SHORT).show();

                } else {

                    new DialogSheet(AddcreditActivity.this)
                            .setTitle("Credit Add")
                            .setMessage("Are you sure ?")
                            .setColoredNavigationBar(true)
                            .setTitleTextSize(20) // In SP
                            .setCancelable(false)
                            .setPositiveButton(android.R.string.ok, new DialogSheet.OnPositiveClickListener() {
                                @Override
                                public void onClick(View v) {
                                    addMemberCredit(AddcreditActivity.this, member, rate_id, amounts);


                                }
                            })
                            .setNegativeButton(android.R.string.cancel, new DialogSheet.OnNegativeClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            })
//                            .setNeutralButton("Neutral", null)
//                            .setBackgroundColor(R.Color.colorAccent) // Your custom background color
                            .setButtonsColorRes(R.color.appcolor) // You can use dialogSheetAccent style attribute instead
                            .show();


                }

            }
        });


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 425) {
//            if (resultCode == RESULT_OK) {
//                addMemberCredit(this,member, rate_id, amounts);
//
//            }
//        }
//    }

    private void addMemberCredit(AddcreditActivity addcreditActivity, String member, String rate_id, String amounts) {

        new RestClient(this).getInstance().get().getallmembercredit(member, rate_id, amounts).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {

                if (response.body() != null) {

                    setResult(Activity.RESULT_OK);
                    finish();
                    Toast.makeText(addcreditActivity, "" + response.body().message, Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(addcreditActivity, "Something Went Wrong..", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {

                Toast.makeText(addcreditActivity, "Something Went Wrong...", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}