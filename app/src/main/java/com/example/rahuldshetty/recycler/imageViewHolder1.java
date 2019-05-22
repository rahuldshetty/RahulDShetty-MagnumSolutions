package com.example.rahuldshetty.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.rahuldshetty.R;

public class imageViewHolder1 extends RecyclerView.ViewHolder {

    ImageView imageView1;

    public imageViewHolder1(@NonNull View itemView) {
        super(itemView);
        imageView1 = itemView.findViewById(R.id.imageView2);

    }
}
