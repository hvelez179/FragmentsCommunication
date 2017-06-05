package com.example.android.fragmentscommunication;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android.fragmentscommunication.R;

public class MainActivity extends AppCompatActivity implements HectorFragment.HectorCommsCallback,
        TomFragment.TomCommsCallback {

    private HectorFragment hectorFragment;
    private TomFragment tomFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hectorFragment= new HectorFragment();
        tomFragment = new TomFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.hector_container,hectorFragment).commit();
        manager.beginTransaction().add(R.id.tom_container,tomFragment).commit();
    }

    @Override
    public void sayHiTom(String greetingMessage) {
        tomFragment.setGreetingMessage(greetingMessage);
        Toast.makeText(this, greetingMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sayByeTom(String greetingMessage) {
        tomFragment.setGreetingMessage(greetingMessage);
        Toast.makeText(this, greetingMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMessageRecieved(String message) {
    }

    @Override
    public void sayHiHector(String greetingMessage) {
        hectorFragment.setGreetingMessage(greetingMessage);
        Toast.makeText(this, greetingMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void sayByeHector(String greetingMessage) {
        hectorFragment.setGreetingMessage(greetingMessage);
        Toast.makeText(this, greetingMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bChangeHector(String bText) {
        hectorFragment.setbText(bText);
    }
    @Override
    public void bChangeTom(String bText) {
        tomFragment.setbText(bText);
    }
}

