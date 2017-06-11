package com.example.android.librarywizard;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.librarywizard.Entities.RandomAPI;
import com.example.android.librarywizard.Entities.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private static final String BASE_URL = "https://randomuser.me/api";
    private static final String RETROFIT_URL = "https://randomuser.me/";

    private static final String RESPONSE_CODE_EXTRA = "RESPONSE_CODE_EXTRA";
    private static final String RESPONSE_MSG_EXTRA = "RESPONSE_MSG_EXTRA";
    private static final String RESPONSE_BODY_EXTRA = "RESPONSE_BODY_EXTRA";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int responseCode = msg.getData().getInt(RESPONSE_CODE_EXTRA);
            String responseMsg = msg.getData().getString(RESPONSE_MSG_EXTRA);
            String responseBody = msg.getData().getString(RESPONSE_BODY_EXTRA);
            postResult(responseCode, responseMsg, responseBody);
        }
    };

    TextView responseCodeTV;
    TextView responseMsgTV;
    TextView responseBodyTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseCodeTV = (TextView) findViewById(R.id.tv_response_code);
        responseMsgTV = (TextView) findViewById(R.id.tv_response_msg);
        responseBodyTV = (TextView) findViewById(R.id.tv_response_body);
        // bindings ( dependency injection, data binding, etc) ...set up what you need. dont just put everything in onCreate.
    }



    @Override
    protected void onResume() {
        super.onResume();
        //doNativeNetworkCall();
        //doOkHttpNetworkCall();
        doRetrofitNetworkCall();
    }

    private void postResult(int responseCode, String responseMsg, String responseBody) {
        String resultCode = String.format("Response Code: %1$d",responseCode);
        String resultMsg = String.format("Response Msg: %1$s", responseMsg);
        String resultBody = String.format("Response Body: %1$s",responseBody);

        responseCodeTV.setText(resultCode);
        responseMsgTV.setText(resultMsg);
        responseBodyTV.setText(resultBody);
    }

    //Native way using HTTPURLConnection
    //Using OkHttp Library
    //Using Retrofit library <--- is the common

    private void doNativeNetworkCall() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Message msg = handler.obtainMessage();
                    Bundle data = new Bundle();
                    String body = "";
                    URL url = new URL(BASE_URL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    int responseCode = connection.getResponseCode();
                    String response = connection.getResponseMessage();
                    Log.d(TAG, "doNativeNetworkCall: Code: " + responseCode + " Message: " + response);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    while ((inputLine = reader.readLine()) != null) {
                        body += inputLine;
                    }
                    Log.d(TAG, "run: Response body: " + body);
                    data.putInt(RESPONSE_CODE_EXTRA, responseCode);
                    data.putString(RESPONSE_MSG_EXTRA, response);
                    data.putString(RESPONSE_BODY_EXTRA, body);
                    msg.setData(data);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void doOkHttpNetworkCall() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL)
                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(okhttp3.Call call, IOException e) {
//                // you handle internet access
//                // your handle retry policies
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(okhttp3.Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    //here is success
//                    final int code = response.code();
//                    final String msg = response.message();
//                    final String body = response.body().toString();
//                    MainActivity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            postResult(code, msg, body);
//                        }
//                    });
//                } else {
//                    //here is error
//                    //create a new token for credentials
//                }
//            }
//        });
    }

    private void doRetrofitNetworkCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RETROFIT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<RandomAPI> call = service.getRandomUser();
        call.enqueue(new retrofit2.Callback<RandomAPI>() {
            @Override
            public void onResponse(Call<RandomAPI> call, retrofit2.Response<RandomAPI> response) {
                if (response.isSuccessful()) {
                    RandomAPI randomAPI = response.body();
                    for (Result result : randomAPI.getResults()) {
                        Log.d(TAG, "onResponse: Name is " + result.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<RandomAPI> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
