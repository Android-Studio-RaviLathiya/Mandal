package com.example.newmandal.service;

import com.example.newmandal.Model.AddCreaditList;
import com.example.newmandal.Model.CommonModel;
import com.example.newmandal.Model.CreditModel;
import com.example.newmandal.Model.InstollmentModal;
import com.example.newmandal.Model.IntresetShow;
import com.example.newmandal.Model.MemberDitailModel;
import com.example.newmandal.Model.MemberModel;
import com.example.newmandal.Model.MemberName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {


    /*Loginpages*/
    @GET("?apicall=login")
    Call<CommonModel> login(@Query("pin") String pin);

    /*new  member */
    @GET("?apicall=addMember")
    Call<CommonModel> addMember(@Query("member_no") String member_no,
                                @Query("name") String name,
                                @Query("address") String address,
                                @Query("mobile") String mobile);

    /*show member in recycleview*/
    @GET("?apicall=getAllMember")
    Call<MemberModel> getAllMember();

    /*destopactivity total caredit,debit */

    @GET("?apicall=getSummary")
    Call<CreditModel> getSummery();


    /*show intereset*/
    @GET("?apicall=getInterestRate")
    Call<IntresetShow> getintrestshow();

    /*add interest*/
    @GET("?apicall=addInterestRate")
    Call<CommonModel> getaddintrest(@Query("rate") String rate);


    /*add credi*/
    @GET("?apicall=addMemberCredit")
    Call<CommonModel> getallmembercredit(@Query("member_id") String member_id,
                                         @Query("interest_rate_id") String interest_id,
                                         @Query("amount") String amount);


    /*memberditail   recycle view member credi*/
    @GET("?apicall=getMemberCredit")
    Call<MemberDitailModel> getmembercreait(@Query("member_id") String member_id);

    /*memberdetail  all data in member*/
    @GET("?apicall=getMemberDetail")
    Call<MemberName> gettopmemberdetail(@Query("member_id") String member_id);

    /*add in instasllment*/
    @GET("?apicall=addInstallment")
    Call<CommonModel> getaddINstallment(@Query("member_id") String member_id,
                                        @Query("credit_id") String credit_id,
                                        @Query("installment") String installment,
                                        @Query("interest") String interest);

    /*sho installment*/
    @GET("?apicall=getInstallment")
    Call<InstollmentModal> getinstollment(@Query("credit_id") String credit_id);


    /*Edit member dirtail*/
    @GET("?apicall=editMemberDetail")
    Call<CommonModel> geteditmember(@Query("member_id") String member_id,
                                        @Query("member_no") String member_no,
                                        @Query("name") String name,
                                        @Query("address") String address,
                                        @Query("mobile") String mobile);

    /*delete*/
    @GET("?apicall=deleteMember")
    Call<CommonModel> getdelete(@Query("member_id") String member_id);

}
