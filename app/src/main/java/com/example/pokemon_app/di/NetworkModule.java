package com.example.pokemon_app.di;

import com.example.pokemon_app.data.datasourceImpl.remote.PokemonNetworkDataSourceImpl;
import com.example.pokemon_app.data.network.APIService;
import com.example.pokemon_app.domain.datasource.remote.PokemonNetworkDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/";

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor(httpLoggingInterceptor);

        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    APIService provideAPIService(Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }


    @Provides
    @Singleton
    PokemonNetworkDataSource provideNetworkDatasource(APIService apiService){
        return new PokemonNetworkDataSourceImpl(apiService);
    }

}

