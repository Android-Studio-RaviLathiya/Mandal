package com.example.newmandal.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newmandal.Model.CommonModel;
import com.example.newmandal.R;
import com.example.newmandal.service.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditIntrestActivity extends AppCompatActivity {

    Button btn_add;
    EditText et_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_intrest);

        btn_add = findViewById(R.id.btn_add);
        et_edit = findViewById(R.id.et_edit);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit = et_edit.getText().toString();


                getintrest(EditIntrestActivity.this, edit);

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

    private void getintrest(EditIntrestActivity editIntrestActivity, String edit) {

        new RestClient(this).getInstance().get().getaddintrest(edit).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.body() != null) {

                    onBackPressed();

                    Toast.makeText(editIntrestActivity, "Add Intrest", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(editIntrestActivity, "Something Went Wrong..", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {

                Toast.makeText(editIntrestActivity, "Something Went Wrong...", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }

}
