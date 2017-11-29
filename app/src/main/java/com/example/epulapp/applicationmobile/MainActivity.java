package com.example.epulapp.applicationmobile;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MenuFragment fragment = new MenuFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.mainFragment,fragment)
                .commit();
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
