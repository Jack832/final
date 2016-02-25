package com.bridgelabz.com.appscreen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Collections;
import java.util.List;

/**
 * Created by bridgelabz4 on 29/1/16.
 */
public class Adapter2 extends RecyclerView.Adapter<Adapter2.MyHolder> {
    private LayoutInflater inflator;
    private Context context2;

    List<MyData> data = Collections.emptyList();

    public Adapter2(Context context, List<MyData> data) {
        inflator = LayoutInflater.from(context);
        this.data = data;
        context2 = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflator.inflate(R.layout.horizontallayout, parent, false);
        MyHolder holder = new MyHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final MyData current = data.get(position);
        holder.icon2.setImageResource(current.mainIcon);

        holder.icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context2.startActivity(new Intent(context2, MediaDemo.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView icon2;

        public MyHolder(View itemView) {
            super(itemView);
            icon2 = (ImageView) itemView.findViewById(R.id.imgho);
            icon2.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}