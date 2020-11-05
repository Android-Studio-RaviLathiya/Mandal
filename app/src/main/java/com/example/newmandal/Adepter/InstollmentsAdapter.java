package com.example.newmandal.Adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newmandal.Model.InstollmentModal;
import com.example.newmandal.Model.MemberModel;
import com.example.newmandal.R;

import java.util.ArrayList;
import java.util.List;

public class InstollmentsAdapter extends RecyclerView.Adapter<InstollmentsAdapter.INSTALLMENT> {

    public List<InstollmentModal.installment> instollment = new ArrayList<InstollmentModal.installment>();
    Context context;

    public InstollmentsAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<InstollmentModal.installment> files) {

        try {
            this.instollment.clear();
            this.instollment.addAll(files);

        } catch (Exception e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }

    public void appendAll(List<InstollmentModal.installment> files) {

        try {
            this.instollment.addAll(files);

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();
    }

    public void clearAll() {
        this.instollment.clear();
        notifyDataSetChanged();
    }

    public void updateList(List<InstollmentModal.installment> list) {
        this.instollment = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public INSTALLMENT onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.instollment_item,parent,false);
        return new INSTALLMENT(view);
    }

    @Override
    public void onBindViewHolder(@NonNull INSTALLMENT holder, int position) {

        holder.date.setText(instollment.get(position).date +"");
        holder.credit.setText(instollment.get(position).amount +"");
        holder.interest.setText(instollment.get(position).interest_amt +"");
    }

    @Override
    public int getItemCount() {
        return instollment.size();
    }

    public class INSTALLMENT extends RecyclerView.ViewHolder {

        TextView credit, date, interest;
        RelativeLayout click;

        public INSTALLMENT(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            credit = itemView.findViewById(R.id.amount);
            interest = itemView.findViewById(R.id.interest);
            click = itemView.findViewById(R.id.click);
        }
    }

}
