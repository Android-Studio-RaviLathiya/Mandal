package com.example.newmandal.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CreditModel implements Serializable {

    @SerializedName("error")
    @Expose
    public boolean error;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("total_credit")
    @Expose
    public String total_credit;
    @SerializedName("total_debit")
    @Expose
    public String total_debit;
    @SerializedName("total_interest")
    @Expose
    public String total_interest;

}
