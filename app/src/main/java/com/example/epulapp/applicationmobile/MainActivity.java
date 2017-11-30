package com.example.epulapp.applicationmobile;

import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener, GameFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MenuFragment fragment = new MenuFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.mainFragment,fragment)
                .commit();

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.broadcast.MY_NOTIFICATION");

        MyReceiver receiver =  new MyReceiver();
        registerReceiver(receiver,filter);

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("onStart","e");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("onPause","e");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("onResume","e");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("onStop","e");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("onDestroy","e");
    }

    @Override
    public void onFragmentInteraction(int id) {
        GameFragment fragment = new GameFragment();
        Log.d("e","e");
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFragment,fragment)
                .addToBackStack("")
                .commit();
    }

    @Override
    public void OnFragmentInteractionGameFragment(int id) {

    }
}
