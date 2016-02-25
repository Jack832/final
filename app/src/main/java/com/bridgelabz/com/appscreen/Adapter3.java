package com.bridgelabz.com.appscreen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by bridgelabz4 on 29/1/16.
 */
public class Adapter3 extends RecyclerView.Adapter<Adapter3.MyHolder>{
    private LayoutInflater inflator;
    private Context context3;

    List<MyData> data= Collections.emptyList();
    public Adapter3(Context context,List<MyData> data){
        inflator = LayoutInflater.from(context);
        this.data=data;
        context3=context;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflator.inflate(R.layout.horizontallayout, parent, false);
        MyHolder holder= new MyHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final MyData current =data.get(position);
        holder.icon3.setImageResource(current.mainIcon);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView icon3;

        public MyHolder(View itemView) {
            super(itemView);
            icon3= (ImageView) itemView.findViewById(R.id.imgho);
            icon3.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(context3,MediaDemo.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            v.getContext().startActivity(i);
        }
    }
}
