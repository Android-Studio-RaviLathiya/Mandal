package com.example.newmandal.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class IntresetShow implements Serializable {

    @SerializedName("error")
    @Expose
    public String error;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("rates")
    @Expose
    public List<Rates> interest_rates;

    public class Rates implements Serializable {


        @SerializedName("id")
        @Expose
        public String id;

        @SerializedName("rate")
        @Expose
        public double rate;


    }
}
