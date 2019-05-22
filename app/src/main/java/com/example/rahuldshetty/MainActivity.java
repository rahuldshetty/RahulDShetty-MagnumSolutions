package com.example.rahuldshetty;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.rahuldshetty.fragments.ErrorFragment;
import com.example.rahuldshetty.fragments.MessageFragment;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private ErrorFragment errorFragment;
    private MessageFragment messageFragment;

    public static Context MainContext ;

    private final Handler handler  = new Handler();

    private boolean alreadyLoaded = true;

    private Button button;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(alreadyLoaded){
                if(!isOnline()) {
                    loadFragment(errorFragment);
                    alreadyLoaded = false;
                }
            }
            else if(isOnline()){
                if(alreadyLoaded==false)
                    loadFragment(messageFragment);
                alreadyLoaded=true;

            }
            handler.postDelayed(this,1000);
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainContext = this;

        frameLayout=findViewById(R.id.frameLayout);
        errorFragment=new ErrorFragment();
        messageFragment=new MessageFragment();

        loadFragment(messageFragment);

       runnable.run();



    }

    @Override
    protected void onResume() {
        super.onResume();
       runnable.run();
    }

    @Override
    protected void onPause() {
        super.onPause();
       handler.removeCallbacks(runnable);
    }




    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }

    public void loadFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout,fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }

 }
