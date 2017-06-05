package com.example.android.fragmentscommunication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TomFragment extends Fragment implements View.OnClickListener {

    private TomCommsCallback callback;
    private Button talkToHectorBT;
    private Button talkToHectorBT2;
    private TextView resultTv;
    int test = 1;



    public interface TomCommsCallback {
        void sayHiHector(String greeting);
        void sayByeHector (String greeting);
        void bChangeTom (String bText);
        void onMessageRecieved(String message);
    }

    public TomFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            callback = (TomCommsCallback) getActivity();
        }catch (ClassCastException e) {
            throw  new ClassCastException(getActivity().toString()
                    + " must implement TomCommsCallback.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tom, container, false);
        talkToHectorBT = (Button) view.findViewById(R.id.talk_hector_bt);
        talkToHectorBT.setOnClickListener(this);
        talkToHectorBT2 = (Button) view.findViewById(R.id.talk_hector_bt2);
        talkToHectorBT2.setOnClickListener(this);
        resultTv = (TextView) view.findViewById(R.id.result_tv_tom);
        return view;
    }

    public  void setGreetingMessage( String greetingMessage) {
        resultTv.setText(greetingMessage);
        talkToHectorBT.setText(greetingMessage);
    }

    public void setbText(String bText) {talkToHectorBT.setText(bText);}
    @Override
    public void  onClick(View v) {
        switch (v.getId()) {
            case R.id.talk_hector_bt:
                if (test == 1){
                    callback.sayHiHector("Hi Hector");
                    callback.bChangeTom("Bye Hector");
                    test =2;
                    break;
                }
                else if (test == 2){
                    callback.sayHiHector("Bye Hector");
                    callback.bChangeTom("Hi Hector");
                    test = 1;
                    break;
                }

            case R.id.talk_hector_bt2:
                callback.sayByeHector("Bye Hector");
                break;

        }
    }

}
