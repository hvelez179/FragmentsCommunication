package com.example.android.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button selectBtn;
    ShapeSelectorView shapeSelector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shapeSelector = (ShapeSelectorView) findViewById(R.id.ssl_view);
        selectBtn = (Button) findViewById(R.id.btn_select);

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You selected: " + shapeSelector.getSelectedSahpe(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
