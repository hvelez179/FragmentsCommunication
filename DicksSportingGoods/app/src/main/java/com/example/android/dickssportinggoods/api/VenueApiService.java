package com.example.android.dickssportinggoods.api;


import com.example.dsgandroidapp.mvp.Model.VenuesResponse;

import retrofit2.http.GET;
import rx.Observable;


public interface VenueApiService {


    @GET("/dsglabs/mobile/api/venue/")
    Observable<VenuesResponse> getVenues();
    // comment 1
    public void main();


}
