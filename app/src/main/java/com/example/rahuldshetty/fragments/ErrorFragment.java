package com.example.rahuldshetty.fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rahuldshetty.MainActivity;
import com.example.rahuldshetty.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ErrorFragment extends Fragment {

    private TextView message;
    private Button button;

    private String internetError = "There seems to be a problem  while connecting to the internet.";
    private String permissionError = "Internet Permission is not granted.";

    private static final int INTERNET_REQ_CODE = 102;

    private String errorData ;

    private View view;

    public ErrorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_error, container, false);
        message = view.findViewById(R.id.errorMsg);

        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED)
        {
            // permission not granted for internet
            errorData = permissionError;
        }
        else{
            errorData = internetError;
        }

        message.setText(errorData);



        return view;
    }



}
