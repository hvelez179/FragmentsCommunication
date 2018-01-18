package com.example.android.classexamappa.list;

import com.example.android.examproject.entities.UserInfo;

import java.util.ArrayList;
import java.util.List;

public interface UserListContract {

    interface View {
        void showDataErrorMessage();

        void showNetworkErrorMessage();

        void showUserList(List<UserInfo> listInfo);
    }

    interface Presenter {
        void populateUserList();
    }

}
