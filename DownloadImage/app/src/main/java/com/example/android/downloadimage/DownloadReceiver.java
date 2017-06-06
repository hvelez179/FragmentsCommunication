package com.example.android.downloadimage;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;


public class DownloadReceiver extends ResultReceiver{

    private MainActivity mainActivity;

    public DownloadReceiver (Handler handler, MainActivity activity) {
        super (handler);
        mainActivity = activity;
    }

    @Override
    protected void onReceiveResult (int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        mainActivity.OnImageDownload((Bitmap) resultData.getParcelable("file"));
    }
}
