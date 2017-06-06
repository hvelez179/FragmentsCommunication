package com.example.android.downloadimage;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.ResultReceiver;
import java.io.InputStream;


public class DownloadService extends IntentService {
    public DownloadService() {
        super("DownloadService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        String urlToDownload = intent.getStringExtra("url");
        ResultReceiver receiver = (ResultReceiver) intent.getParcelableExtra("receiver");
        Bundle b = new Bundle();
        Bitmap bitmap = null;
        try {
            InputStream input = new java.net.URL(urlToDownload).openStream();
            bitmap = BitmapFactory.decodeStream(input);
            b.putParcelable("file", bitmap);
            receiver.send(0, b);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
