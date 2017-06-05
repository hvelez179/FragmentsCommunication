package com.example.android.fragmentscommunication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HectorFragment extends Fragment implements View.OnClickListener {

    private HectorCommsCallback callback;
    private Button talkToTomBT;
    private Button talkToTomBT2;
    private TextView resultTv;
    int test = 1;

    public interface HectorCommsCallback {
        void sayHiTom (String greeting);
        void sayByeTom (String greeting);
        void bChangeHector (String bText);
        void onMessageRecieved(String message);
    }

    public HectorFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            callback = (HectorCommsCallback) getActivity();
        }catch (ClassCastException e) {
            throw  new ClassCastException(getActivity().toString()
                    + " must implement HectorCommsCallback.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hector, container, false);
        talkToTomBT = (Button) view.findViewById(R.id.talk_tom_bt);
        talkToTomBT.setOnClickListener(this);
        talkToTomBT2 = (Button) view.findViewById(R.id.talk_tom_bt2);
        talkToTomBT2.setOnClickListener(this);
        resultTv = (TextView) view.findViewById(R.id.result_tv_hector);
        return view;
    }

    public  void setGreetingMessage( String greetingMessage) {
        resultTv.setText(greetingMessage);
        talkToTomBT.setText(greetingMessage);
    }

    public void setbText(String bText) {talkToTomBT.setText(bText);}
    @Override
    public void  onClick(View v) {
        switch (v.getId()) {
            case R.id.talk_tom_bt:
                if(test ==1) {
                    callback.sayHiTom("Hi Tom");
                    callback.bChangeHector("Bye Tom");
                    test = 2;
                    break;
                }
                else if (test == 2) {
                    callback.sayHiTom("Bye Tom");
                    callback.bChangeHector("Hi Tom");
                    test = 1;
                    break;
                }

            case R.id.talk_tom_bt2:
                callback.sayByeTom("Bye Tom");
                break;

        }
    }

}
