package com.example.rahuldshetty.models;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class MessageList {

    private ArrayList<Message> messages;

    public MessageList(){
        messages = new ArrayList<>();
    }

    public int currentMessage = -1;

    public void addTextMessage(String from,String data){
        Message msg = new Message();
        msg.setType("text");
        msg.setFrom(from);
        msg.setText(data);
        messages.add(msg);
    }

    public Message get(int index){
        return messages.get(index);
    }

    public int size(){
        return messages.size();
    }

    public void add(Message msg){
        messages.add(msg);
    }

    public void addImageMessage(String from, Bitmap data){
        Message msg = new Message();
        msg.setType("image");
        msg.setFrom(from);
        msg.setImage(data);
        messages.add(msg);
    }

    public MessageList getMessages(int index){
        if(index<messages.size()){
            MessageList messageList = new MessageList();
            for(int i=0;i<index;i++)
                messageList.add(messages.get(i));
            return messageList;
        }
        return null;
    }

    public void addMessages(MessageList messageList){
        messages = messageList.getList();
    }

    public ArrayList<Message> getList(){
        return messages;
    }

    public void clear(){
        messages.clear();
    }

    public Message getMessage(){
        if(currentMessage + 1 < messages.size() )
        {
            currentMessage++;
            return messages.get(currentMessage);
        }
        else
            return null;
    }

}
