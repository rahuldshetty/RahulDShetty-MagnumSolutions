package com.example.rahuldshetty.fragments;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rahuldshetty.MainActivity;
import com.example.rahuldshetty.R;
import com.example.rahuldshetty.models.Message;
import com.example.rahuldshetty.models.MessageList;
import com.example.rahuldshetty.recycler.Adapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    private View view;

    private static MessageList messageList,currentList;
    private static RecyclerView recyclerView;
    private static Adapter adapter;
    private Button add;
    private ConstraintLayout constraintLayout;


    public MessageFragment() {
        // Required empty public constructor
        //load messages
        messageList = new MessageList();
        currentList = new MessageList();

        try {
            //adding image message examples
            Message m = new Message();
            m.setType("image");
            m.setFrom("self");
            m.setImage(BitmapFactory.decodeStream(MainActivity.MainContext.getAssets().open("images/img.jpeg")));
            messageList.add(m);
            Message m2 = new Message();
            m2.setType("image");
            m2.setFrom("others");
            m2.setImage(BitmapFactory.decodeStream(MainActivity.MainContext.getAssets().open("images/img.jpeg")));
            messageList.add(m2);


            InputStream inputStream = MainActivity.MainContext.getAssets().open("data.csv");
            System.out.println(inputStream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line=reader.readLine())!=null){

                Message msg=new Message();
                if(line.startsWith("human"))
                    msg.setFrom("self");
                else
                    msg.setFrom("others");

                msg.setType("text");
                msg.setText(line.substring(7,line.length()-3));

                messageList.add(msg);
            }
        }
        catch(Exception e){
            System.out.println("Caught Exception"+e);
        }

        System.out.println(messageList.size());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_message, container, false);

        recyclerView=view.findViewById(R.id.recyclerView);



        adapter = new Adapter(currentList,MainActivity.MainContext);

        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.MainContext);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        size=currentList.size();


        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            private int CLICK_ACTION_THRESHOLD = 200;
            private float startX;
            private float startY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        float endX = event.getX();
                        float endY = event.getY();
                        if (isAClick(startX, endX, startY, endY)) {
                            if(MainActivity.isOnline()) {
                                currentList.add(messageList.getMessage());
                                adapter.notifyDataSetChanged();
                                recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
                            }
                            else{
                                //check thread will execute as delayed
                            }

                        }
                        break;
                }
                v.getParent().requestDisallowInterceptTouchEvent(true); //specific to my project
                return false; //specific to my project
            }

            private boolean isAClick(float startX, float endX, float startY, float endY) {
                float differenceX = Math.abs(startX - endX);
                float differenceY = Math.abs(startY - endY);
                return !(differenceX > CLICK_ACTION_THRESHOLD/* =5 */ || differenceY > CLICK_ACTION_THRESHOLD);
            }
        });

        return view;

    }

    public static void  call(){
        currentList.add(messageList.getMessage());
        adapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(adapter.getItemCount()-1);
    }

    int size;
    @Override
    public void onResume() {
        super.onResume();
        if(size==0)
        {
            currentList.add(messageList.getMessage());
        }
        else {
            currentList.clear();
            adapter.notifyItemRangeRemoved(0, size);
            currentList.addMessages(messageList.getMessages(size));
        }
        adapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(adapter.getItemCount()-1);
    }

    @Override
    public void onPause() {
        super.onPause();
        size=currentList.size();

    }
}
