package com.example.android.dickssportinggoods.dependencyInjection.component;

import android.content.Context;

import com.example.dsgandroidapp.dependenctInjection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Retrofit exposeRetrofit();

    Context exposeContext();
}
