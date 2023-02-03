package com.example.hpdd.match;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import com.example.hpdd.R;
import com.example.hpdd.lg.expertUser;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class expertAdapter extends RecyclerView.Adapter<expertAdapter.CustomViewHolder>{

    private static final String TAG="";
    private ArrayList<expertUser> arrayList;
    private Context context;
    private Intent intent;

    public expertAdapter(ArrayList<expertUser> arrayList, Context context) {
        this.arrayList=arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expert_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        Log.d(TAG,"holder 출력");

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {

        //holder.iv_profile.setImageResource(arrayList.get(position).getIv_profile());
        holder.tv_name.setText(arrayList.get(position).getExpert_name());
        holder.tv_sex.setText(arrayList.get(position).getExpert_sex());
        holder.tv_field.setText(arrayList.get(position).getExpert_type());

        Log.d(TAG,"리사이클러뷰에 출력");
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(),biography.class);
                intent.putExtra("number",position);
                intent.putExtra("name",arrayList.get(position).getExpert_name());
                intent.putExtra("phone",arrayList.get(position).getExpert_type());
                intent.putExtra("tv",arrayList.get(position).getExpert_type());
                intent.putExtra("license",arrayList.get(position).getExpert_type());
                intent.putExtra("age",arrayList.get(position).getExpert_age());
                intent.putExtra("sex",arrayList.get(position).getExpert_sex());
                intent.putExtra("field",arrayList.get(position).getExpert_type());


                v.getContext().startActivity(intent);
                //String curname = holder.tv_name.getText().toString();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(holder.getAdapterPosition());
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position){
        try {
            arrayList.remove(position);
            notifyItemRemoved(position);
        }catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder {

        //ImageView iv_profile;
        TextView tv_name, tv_sex, tv_field;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            //this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_sex = itemView.findViewById(R.id.tv_sex);
            this.tv_field = itemView.findViewById(R.id.tv_field);

        }
    }
    }

