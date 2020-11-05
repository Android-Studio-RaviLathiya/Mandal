package com.example.newmandal.Model;

import android.widget.TextView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class InstollmentModal implements Serializable {

    @SerializedName("error")
    @Expose
    public String error;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("remaining_credit")
    @Expose
    public String remaining_credit;


    @SerializedName("interest_amount")
    @Expose
    public String interest_amount;




    @SerializedName("installment_remaining")
    @Expose
    public String installment_remaining;





    @SerializedName("installment")
    @Expose
    public List<installment> installment;


    public class installment implements Serializable {


        @SerializedName("amount")
        @Expose
        public String amount;

        @SerializedName("interest_amt")
        @Expose
        public String interest_amt;

        @SerializedName("date")
        @Expose
        public String date;


    }
}
