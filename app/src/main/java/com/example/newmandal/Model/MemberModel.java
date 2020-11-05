package com.example.newmandal.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MemberModel implements Serializable {

    @SerializedName("total_member")
    @Expose
    public int total_member;



    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("error")
    @Expose
    public String error;




    @SerializedName("members")
    @Expose
    public List<MemberData> member_list;


    public class MemberData implements Serializable {
        @SerializedName("id")
        @Expose
        public String id;
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

        @SerializedName("member_credit")
        @Expose
        public String member_credit;



        @SerializedName("remaining_member_credit")
        @Expose
        public String remaining_member_credit;






    }

//
//    @SerializedName("member_ detail")
//    @Expose
//    public memberDetail member_detail;
//
//    public class memberDetail implements Serializable {
//        @SerializedName("member_id")
//        @Expose
//        public String member_id;
//
//        @SerializedName("member_no")
//        @Expose
//        public String member_no;
//        @SerializedName("name")
//        @Expose
//        public String name;
//        @SerializedName("address")
//        @Expose
//        public String address;
//        @SerializedName("mobile")
//        @Expose
//        public String mobile;
//        @SerializedName("credit")
//        @Expose
//        public String credit;
//        @SerializedName("installment_amt")
//        @Expose
//        public String installment_amt;
//        @SerializedName("interest_amt")
//        @Expose
//        public String interest_amt;
//
//    }

//    @SerializedName("member_installment")
//    @Expose
//    public List<memberInstallment> member_installment;
//    @SerializedName("credit_id")
//    @Expose
//    public String credit_id;
//
//    public class memberInstallment implements Serializable {
//
//        @SerializedName("installment_amt")
//        @Expose
//        public String installmentAmt;
//
//        @SerializedName("interest_amt")
//        @Expose
//        public String interestAmt;
//
//        @SerializedName("date")
//        @Expose
//        public String installmentDate;
//
//    }

}
