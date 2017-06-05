package com.example.android.backgroundwork;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() +"_TAG";
    private static final String MESSAGE_EXTRA = "com.example.android.backgroundwork.MESSAGE_EXTRA";

    TextView resultTV;
    MyThread myThread;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String message = msg.getData().getString(MESSAGE_EXTRA);
            setResultTextView(message);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTV = (TextView) findViewById(R.id.tv_result);
        myThread = new MyThread();
        myThread.start();
//        setResultTextView("");
    }

    public void callMain(View view) {
        try {
            String message = "Executed in main thread.";
            Thread.sleep(800);
            setResultTextView(message);
            Log.d(TAG, "callMain");
            }catch(InterruptedException e){
                e.printStackTrace();
            }

    }

    public void fromHandler(View view) {
        final String message = "From thread to handler";
        Thread thread = new Thread() {
            @Override
            public void run() {
                Message msg = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString(MESSAGE_EXTRA, message);
                msg.setData(bundle);
                try {
                    sleep(800);

                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendMessage(msg);
            }
        };

    }

    public void fromThread(View view) {
        final String message = "From backgrount thread.";
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    try {
                        wait(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // setResultTextView(message);
                    Log.d(TAG, "run" + message + Thread.currentThread().getName());
                        }
                    }
                });
            thread.start();
            }

    public void withLooper(View view) {
        myThread.handler.post(new Runnable() {
            @Override
            public void run() {
                String message = "From Thread with Looper";
                String thread = Thread.currentThread().getName();
                Log.d(TAG, "run: " + message + " " + thread);
            }
        });

    }

    private void setResultTextView(String message) {
        String result = String.format(getString(R.string.lbl_msg_reslut), message);
        resultTV.setText(result);
    }
}
