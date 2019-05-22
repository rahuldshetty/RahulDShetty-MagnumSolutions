package com.example.rahuldshetty.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.rahuldshetty.R;

import org.w3c.dom.Text;

public class messageHolder extends RecyclerView.ViewHolder {

    TextView msg1;


    public messageHolder(@NonNull View itemView) {
        super(itemView);
        msg1 = itemView.findViewById(R.id.single_content1);

        
    }
}

