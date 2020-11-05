package com.example.newmandal.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newmandal.InputFilterMinMax;
import com.example.newmandal.Model.CommonModel;
import com.example.newmandal.R;
import com.example.newmandal.service.RestClient;
import com.marcoscg.dialogsheet.DialogSheet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddInstollmentActivity extends AppCompatActivity {

    EditText amount;
    TextView etinterest;
    Button add_btn;
    String member_id, credit_id, total_credit,interest_amount;
    String am;
    String interest;
    TextView credit;
    String creditamount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instolment);

        amount = findViewById(R.id.amount);
        etinterest = findViewById(R.id.insterest);
        add_btn = findViewById(R.id.btn_add);
        credit = findViewById(R.id.credit);

        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null) {
            member_id = bundle1.getString("memberid");
            credit_id = bundle1.getString("creditid");
            total_credit = bundle1.getString("total_credit");
            interest_amount = bundle1.getString("interest_amountss");
        }

        credit.setText(total_credit);
        creditamount = total_credit;

        etinterest.setText(interest_amount);


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


        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer addCreditInt = new Integer(creditamount.toString());
                Integer txtInstallmentInt = new Integer(amount.getText().toString());

//                am = amount.getText().toString();
                interest = etinterest.getText().toString();



//                if (am.equals("")){
//
//                    amount.setError("Enter Amount");

                if (txtInstallmentInt > addCreditInt) {

                    amount.setError("Installment should less than "+creditamount);

                } else if (interest.equals("")) {
                    etinterest.setError("Enter Interest");

                } else {

                    am = amount.getText().toString();
                    new DialogSheet(AddInstollmentActivity.this)
                            .setTitle("Instollment Add")
                            .setMessage("Are you sure ?")
                            .setColoredNavigationBar(true)
                            .setTitleTextSize(20) // In SP
                            .setCancelable(false)
                            .setPositiveButton(android.R.string.ok, new DialogSheet.OnPositiveClickListener() {

                                @Override
                                public void onClick(View v) {
                                    addinstollment(AddInstollmentActivity.this, member_id, credit_id, am, interest);

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

    private void addinstollment(AddInstollmentActivity addInstolmentActivity, String member_id, String credit_id, String am, String interest) {

        new RestClient(this).getInstance().get().getaddINstallment(member_id, credit_id, am, interest).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {


                if (response.body() != null) {
                    onBackPressed();

                    Toast.makeText(AddInstollmentActivity.this, "" + response.body().message, Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(AddInstollmentActivity.this, "Something Went Wrong..", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {

                Toast.makeText(addInstolmentActivity, "Something Went Wrong...", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }

}
