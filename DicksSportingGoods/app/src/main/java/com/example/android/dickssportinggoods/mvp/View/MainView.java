package com.example.android.dickssportinggoods.mvp.View;


import com.example.dsgandroidapp.mvp.Model.VenuesResponseVenues;

import java.util.List;


public interface MainView extends BaseView {
    void onVenueLoaded(List<VenuesResponseVenues> venues);

    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);

    void onClearItems();
}
