package com.example.newmandal.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AddCreaditList implements Serializable {


    @SerializedName("credit_details")
    @Expose
    public List<creditdetail> credit_details;

    public class creditdetail implements Serializable {

        @SerializedName("member_id")
        @Expose
        public String member_id;

        @SerializedName("interest_rate_id")
        @Expose
        public int interesMemt_rate_id;

        @SerializedName("amount")
        @Expose
        public String amount;

    }


}
