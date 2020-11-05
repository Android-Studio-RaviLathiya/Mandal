package com.example.newmandal.Adepter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newmandal.Activity.AddcreditActivity;
import com.example.newmandal.Model.IntresetShow;
import com.example.newmandal.R;

import java.util.ArrayList;
import java.util.List;

public class IntrestAdepter extends RecyclerView.Adapter<IntrestAdepter.Intrest> {

    String member_id;

    public List<IntresetShow.Rates> ratesList = new ArrayList<IntresetShow.Rates>();
    public Activity mContext;

    public IntrestAdepter(Activity mContext) {
        this.mContext = mContext;
    }

    public void addAll(List<IntresetShow.Rates> files) {

        try {
            this.ratesList.clear();
            this.ratesList.addAll(files);

        } catch (Exception e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }


    public void appendAll(List<IntresetShow.Rates> files) {

        try {
            this.ratesList.addAll(files);

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();
    }

    public void clearAll() {
        this.ratesList.clear();
        notifyDataSetChanged();
    }

    public void updateList(List<IntresetShow.Rates> list) {
        this.ratesList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public Intrest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.intrest_item, parent, false);
        return new Intrest(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Intrest holder, int position) {

        holder.text.setText(ratesList.get(position).id);
        holder.text.setText(ratesList.get(position).rate + "");

        Bundle bundle1 = mContext.getIntent().getExtras();
        if (bundle1 != null) {
            member_id = bundle1.getString("member_id");

        }

        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, AddcreditActivity.class);
                intent.putExtra("id", ratesList.get(position).id);
                intent.putExtra("rate", ratesList.get(position).rate + "");
                intent.putExtra("ids", member_id);
                mContext.startActivity(intent);
                ((Activity) mContext).finish();
                Toast.makeText(mContext, "" + ratesList.get(position).rate, Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return ratesList.size();
    }

    public class Intrest extends RecyclerView.ViewHolder {

        TextView text;

        public Intrest(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
        }
    }
}
