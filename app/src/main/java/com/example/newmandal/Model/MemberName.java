package com.example.newmandal.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MemberName implements Serializable {

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("error")
    @Expose
    public String error;

    @SerializedName("member_no")
    @Expose
    public String member_no;


    @SerializedName("name")
    @Expose
    public String name;




 @SerializedName("address")
    @Expose
    public String address;


    @SerializedName("mobile")
    @Expose
    public String mobile;

    @SerializedName("credit")
    @Expose
    public String credit;

    @SerializedName("debit")
    @Expose
    public String debit;


    @SerializedName("interest")
    @Expose
    public String interest;


}


