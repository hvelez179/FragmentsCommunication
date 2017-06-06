package com.example.android.downloadimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    DownloadReceiver mReceiver;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mReceiver = new DownloadReceiver(new Handler(), this);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void OnImageDownload(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, DownloadService.class);
        intent.putExtra("url", "https://s-media-cache-ak0.pinimg.com/736x/1d/8c/d9/1d8cd9fd21c78aca80089fe32571c0a5.jpg");
        intent.putExtra("receiver", new DownloadReceiver(new Handler(), this));
        startService(intent);
    }
}
