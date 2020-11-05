package com.example.newmandal.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MemberDitailModel implements Serializable {


    @SerializedName("member_id")
    @Expose
    public String member_id;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("error")
    @Expose
    public String error;


        @SerializedName("credits")
    @Expose
    public List<credits> credits_member;

    public class credits implements Serializable {

        @SerializedName("id")
        @Expose
        public String id;

        @SerializedName("amount")
        @Expose
        public String amount;

        @SerializedName("rate")
        @Expose
        public String rate;

        @SerializedName("date")
        @Expose
        public String date;

         @SerializedName("remaining_credit")
        @Expose
        public String remaining_credit;






    }
}
