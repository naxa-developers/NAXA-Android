package com.NaxaCo.Naxa.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.NaxaCo.Naxa.DbAccess.Conversion_Area_Dto;
import com.NaxaCo.Naxa.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by cri_r on 08/02/2018.
 */

public class MyProfile_adapter extends RecyclerView.Adapter<MyProfile_adapter.MyViewHolder>{
    private Context context;
    private List<Conversion_Area_Dto> conversion_area_dtoList;


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public ImageView thumbnail;
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
    public MyProfile_adapter(Context context, List<Conversion_Area_Dto> conversion_area_dtoList){
        this.context=context;
        this.conversion_area_dtoList=conversion_area_dtoList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_profile_album,parent,false);
    return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Conversion_Area_Dto IcConversion_area_dto=conversion_area_dtoList.get(position);
//        holder.title.setText(IcConversion_area_dto.getName());
        Glide.with(context).load(IcConversion_area_dto.getImage()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return conversion_area_dtoList.size();
    }


}
