package com.example.pokemon_app;

import android.app.Application;

import com.example.pokemon_app.di.AppComponent;
import com.example.pokemon_app.di.DaggerAppComponent;
import com.example.pokemon_app.di.DatabaseModule;
import com.example.pokemon_app.di.NetworkModule;

public class App  extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
       appComponent = DaggerAppComponent.builder().networkModule(new NetworkModule()).databaseModule(new DatabaseModule(this)).build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
