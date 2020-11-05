package com.example.newmandal.Adepter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newmandal.Activity.DesbordActivity;
import com.example.newmandal.Activity.EditmemberActivity;
import com.example.newmandal.Activity.MemberDetailActivity;
import com.example.newmandal.Model.CommonModel;
import com.example.newmandal.Model.MemberModel;
import com.example.newmandal.R;
import com.example.newmandal.service.RestClient;
import com.marcoscg.dialogsheet.DialogSheet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.media.CamcorderProfile.get;

public class MemberAdeptare extends RecyclerView.Adapter<MemberAdeptare.Myholder> {

    String mores;
    private static final int REQUEST_CALL = 1;
    String numbers;


    private List<MemberModel.MemberData> memberDetailList = new ArrayList<MemberModel.MemberData>();
    public Activity mContext;
    private int position;

    public MemberAdeptare(Activity mContext) {
        this.mContext = mContext;
    }


    public void addAll(List<MemberModel.MemberData> files) {

        try {
            this.memberDetailList.clear();
            this.memberDetailList.addAll(files);

        } catch (Exception e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }

    public void appendAll(List<MemberModel.MemberData> files) {

        try {
            this.memberDetailList.addAll(files);

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();
    }

    public void clearAll() {
        this.memberDetailList.clear();
        notifyDataSetChanged();
    }

    public void updateList(List<MemberModel.MemberData> list) {
        this.memberDetailList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.member_item, parent, false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {


        holder.tvname.setText(memberDetailList.get(position).name);
        holder.tvnumber.setText(memberDetailList.get(position).member_no);
        holder.mobile.setText(memberDetailList.get(position).mobile);
        holder.member_credit.setText(memberDetailList.get(position).member_credit);
        holder.remaining_member_credit.setText(memberDetailList.get(position).remaining_member_credit);
        numbers = holder.mobile.getText().toString();


//        holder.mobile.setText(memberDetailList.get(position).mobile);
//        holder.tvname.setText(memberDetailList.get(position).id);

        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MemberDetailActivity.class);
                intent.putExtra("member_id", memberDetailList.get(position).id);
                mContext.startActivityForResult(intent, 425);
            }
        });


        holder.more.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {

                MenuBuilder menuBuilder = new MenuBuilder(mContext);
                new SupportMenuInflater(mContext).inflate(R.menu.calleditdelet, menuBuilder);
                menuBuilder.setCallback(new MenuBuilder.Callback() {
                    @Override
                    public boolean onMenuItemSelected(MenuBuilder menu, MenuItem menuItem) {
                        // your "setOnMenuItemClickListener" code goes here
                        switch (menuItem.getItemId()) {
                            case R.id.call:


                                new DialogSheet(mContext)
                                        .setTitle("Calling " + memberDetailList.get(position).name)
                                        .setMessage("" + memberDetailList.get(position).mobile)
                                        .setColoredNavigationBar(true)
                                        .setTitleTextSize(20) // In SP
                                        .setCancelable(false)
                                        .setPositiveButton(R.string.call, new DialogSheet.OnPositiveClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) !=

                                                        PackageManager.PERMISSION_GRANTED
                                                ) {

                                                    ActivityCompat.requestPermissions(mContext, new String[]
                                                            {Manifest.permission.CALL_PHONE}, REQUEST_CALL);

                                                } else {

                                                    String dail = "tel:" + memberDetailList.get(position).mobile;
                                                    mContext.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dail)));


                                                }


                                            }
                                        })
                                        .setNegativeButton(android.R.string.cancel, new DialogSheet.OnNegativeClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                            }
                                        })
//                            .setNeutralButton("Neutral", null)
//                            .setBackgroundColor(R.Color.colorAccent) // Your custom background color
                                        .setButtonsColorRes(R.color.call) // You can use dialogSheetAccent style attribute instead
                                        .show();


                                return true;

                            case R.id.edit:

                                Intent inten = new Intent(mContext, EditmemberActivity.class);
                                inten.putExtra("name", memberDetailList.get(position).name);
                                inten.putExtra("mobile", memberDetailList.get(position).mobile);
                                inten.putExtra("id", memberDetailList.get(position).id);
                                inten.putExtra("address", memberDetailList.get(position).address);
                                inten.putExtra("member_no", memberDetailList.get(position).member_no);
                                mContext.startActivityForResult(inten, 425);


                                return true;

                            case R.id.delet:

                                new DialogSheet(mContext)
                                        .setTitle("Delete " + memberDetailList.get(position).name)
                                        .setMessage("Are you sure?")
                                        .setColoredNavigationBar(true)
                                        .setTitleTextSize(20) // In SP
                                        .setCancelable(false)
                                        .setPositiveButton(R.string.delete, new DialogSheet.OnPositiveClickListener() {
                                            @Override
                                            public void onClick(View v) {


                                                dalete(mContext, memberDetailList.get(position).id);


                                            }
                                        })
                                        .setNegativeButton(android.R.string.cancel, new DialogSheet.OnNegativeClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                            }
                                        })
                                        .setButtonsColorRes(R.color.credit) // You can use dialogSheetAccent style attribute instead
                                        .show();




                                return true;

                        }
                        return false;
                    }

                    @Override
                    public void onMenuModeChange(MenuBuilder menu) {
                    }
                });
                MenuPopupHelper menuHelper = new MenuPopupHelper(mContext, menuBuilder, v);
                menuHelper.setForceShowIcon(true); // show icons!!!!!!!!
                menuHelper.show();


            }
        });


    }

    private void dalete(Activity mContext, String id) {

        new RestClient(mContext).getInstance().get().getdelete(id).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {

                if (response.body() != null) {

                    ((DesbordActivity) mContext).getmember(mContext);
                    ((DesbordActivity) mContext).getcredit(mContext);
                    Toast.makeText(mContext, "" + response.body().message, Toast.LENGTH_SHORT).show();


                } else {

                    Toast.makeText(mContext, "Something Went Wrong..", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {

                Toast.makeText(mContext, "Something Went Wrong..", Toast.LENGTH_SHORT).show();

            }
        });


//        private Activity mActivity;
//public holder(View itemView,Activity mActivity) {
//            super(itemView);
//            this.mContext=mActivity;
//        }

    }


    @Override
    public int getItemCount() {
        return memberDetailList.size();
    }


    public class Myholder extends RecyclerView.ViewHolder {

        LinearLayout click;
        TextView tvname, mobile, tvnumber, member_credit,remaining_member_credit;
        Button more;


        public Myholder(@NonNull View itemView) {
            super(itemView);


            click = itemView.findViewById(R.id.click);
            tvname = itemView.findViewById(R.id.tvname);
            mobile = itemView.findViewById(R.id.mobile);
            tvnumber = itemView.findViewById(R.id.tvnumber);
            member_credit = itemView.findViewById(R.id.member_credit);
            remaining_member_credit = itemView.findViewById(R.id.remaining_member_credit);

            more = itemView.findViewById(R.id.more);

        }
    }

}
