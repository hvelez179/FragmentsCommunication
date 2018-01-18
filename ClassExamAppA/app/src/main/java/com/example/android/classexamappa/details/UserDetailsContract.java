package com.example.android.classexamappa.details;

import android.os.Bundle;

import com.example.android.examproject.entities.UserInfo;

import java.util.ArrayList;

public interface UserDetailsContract {
    interface View {
        void DisplayUserData(String url, String name, String address, String email);

        void ErrorDisplayingUserData();
    }

    interface Presenter {
        void GetUserData(Bundle b);
    }
}

