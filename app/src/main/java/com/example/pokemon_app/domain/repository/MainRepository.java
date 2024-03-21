package com.example.pokemon_app.domain.repository;

import androidx.lifecycle.LiveData;

import com.example.pokemon_app.data.models.pokemonList.ResultsResponse;

import java.util.List;

public interface MainRepository {

    public void fetchPokemonList(int offset);
    public LiveData<List<ResultsResponse>> getPokemonListLiveData();
    public LiveData<Boolean> getProgressBarLiveData();
    public  LiveData<Boolean> getSwipeRefreshLayoutLiveData();
    public  LiveData<String> getToastLiveData();

    public void resetValuesLiveData();

    public void getDisposableToUnsubscribe();
}
