package com.example.rahuldshetty.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.rahuldshetty.R;

public class messageHolder2 extends RecyclerView.ViewHolder {


        TextView msg2;

        public messageHolder2(@NonNull View itemView) {
            super(itemView);
            msg2 = itemView.findViewById(R.id.single_content2);


        }

}
