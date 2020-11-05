package com.example.newmandal.Adepter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newmandal.Activity.IntrestListActivity;
import com.example.newmandal.Activity.ShowInstolmentActivity;
import com.example.newmandal.Model.IntresetShow;
import com.example.newmandal.Model.MemberDitailModel;
import com.example.newmandal.Model.CreditModel;
import com.example.newmandal.Model.MemberModel;
import com.example.newmandal.R;

import java.util.ArrayList;
import java.util.List;

public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.CREDIT>{

    String member_id;


    public  List<MemberDitailModel.credits> creditdetails = new ArrayList<MemberDitailModel.credits>();
    public Activity mContext;

    public CreditAdapter(Activity mContext) {
        this.mContext = mContext;
    }

    public void addAll(List<MemberDitailModel.credits> files) {

        try {
            this.creditdetails.clear();
            this.creditdetails.addAll(files);

        } catch (Exception e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }


    public void appendAll(List<MemberDitailModel.credits> files) {

        try {
            this.creditdetails.addAll(files);

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();
    }
    public void clearAll() {
        this.creditdetails.clear();
        notifyDataSetChanged();
    }

    public void updateList(List<MemberDitailModel.credits> list) {
        this.creditdetails = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CREDIT onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.member_credit_item,parent,false);

        return new CREDIT(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CREDIT holder, int position) {

        Bundle bundle1 = mContext.getIntent().getExtras();
        if (bundle1 != null) {
            member_id = bundle1.getString("member_id");

        }


        holder.credit.setText(creditdetails.get(position).amount +"");
        holder.interest.setText(creditdetails.get(position).rate +"");
        holder.date.setText(creditdetails.get(position).date +"");
        holder.remaining_credit.setText(creditdetails.get(position).remaining_credit +"");


        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShowInstolmentActivity.class);
                intent.putExtra("memberid", member_id);
                intent.putExtra("creditid", creditdetails.get(position).id + "");

                mContext.startActivityForResult(intent, 426);

            }
        });




    }

    @Override
    public int getItemCount() {
        return creditdetails.size();
    }

    public class CREDIT extends RecyclerView.ViewHolder {

        TextView credit,date,interest,remaining_credit;
        RelativeLayout click;

        public CREDIT(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            credit = itemView.findViewById(R.id.credit);
            interest = itemView.findViewById(R.id.interest);
            click = itemView.findViewById(R.id.click);
            remaining_credit = itemView.findViewById(R.id.remaining_credit);
        }
    }
}
