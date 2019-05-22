package com.example.rahuldshetty.recycler;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rahuldshetty.MainActivity;
import com.example.rahuldshetty.R;
import com.example.rahuldshetty.fragments.MessageFragment;
import com.example.rahuldshetty.models.Message;
import com.example.rahuldshetty.models.MessageList;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  implements View.OnClickListener {

    MessageList messageList;

    Context context;

    public Adapter(MessageList list,Context context){
        messageList=  list;
        this.context = context;
    }

    public int getItemViewType(int position){
        if(messageList.get(position).getType().equals("text")) {
            if (messageList.get(position).getFrom().equals("self")) {
                return 0;
            }
            return 1;
        }
        else{
            if(messageList.get(position).getFrom().equals("self")){
                return 4;
            }
            return 3;

        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;



        switch (i){
            case 0:
                view =layoutInflater.inflate(R.layout.message,viewGroup,false);
                messageHolder holder = new messageHolder(view);
                return holder;

            case 1:
                view =layoutInflater.inflate(R.layout.message2,viewGroup,false);
                messageHolder2 holder2 = new messageHolder2(view);
                return holder2;

            case 4:
                view = layoutInflater.inflate(R.layout.message4,viewGroup,false);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       //to open image
                    }
                });
                return new imageViewHolder1(view);

            case 3:
                view = layoutInflater.inflate(R.layout.message3,viewGroup,false);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //to open image

                    }
                });
                return new imageViewHolder3(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch(viewHolder.getItemViewType()){
            case 0:
                messageHolder holder1 = (messageHolder)viewHolder;
                holder1.msg1.setText(messageList.get(i).getText());
                break;

            case 1:
                messageHolder2 holder2 = (messageHolder2)viewHolder;
                holder2.msg2.setText(messageList.get(i).getText());
                break;

            case 4:
                ((imageViewHolder1)viewHolder).imageView1.setImageBitmap( messageList.get(i).getImage() );
                break;

            case 3:

                ((imageViewHolder3)viewHolder).imageView1.setImageBitmap( messageList.get(i).getImage() );
                break;
        }
    }





    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public void onClick(View v) {
        MessageFragment.call();
    }
}
