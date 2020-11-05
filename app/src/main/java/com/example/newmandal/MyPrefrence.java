package com.example.newmandal;

import android.app.Application;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class MyPrefrence extends Application {

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences("my", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void setmemberid(String memberid) {
        editor.putString("memberid", memberid).commit();
    }

    public static String getmemberid() {
        return sharedPreferences.getString("memberid", "");
    }


    public static void setamount(String amount) {
        editor.putString("amount", amount).commit();
    }

    public static String getamount() {
        return sharedPreferences.getString("amount", "");
    }

     public static void setmmid(String mmid) {
        editor.putString("mmid", mmid).commit();
    }

    public static String getmmid() {
        return sharedPreferences.getString("mmid", "");
    }





    public static void setamountvalue(boolean bamountvalue) {
        editor.putBoolean("bamountvalue", bamountvalue).commit();
    }

    public static boolean getamountvalue() {
        return sharedPreferences.getBoolean("bamountvalue", false);
    }


}


