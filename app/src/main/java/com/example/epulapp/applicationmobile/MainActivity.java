package com.example.epulapp.applicationmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("e","e");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("e","e");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("e","e");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("e","e");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("e","e");
    }
}
