package com.example.pokemon_app.domain.repository;

import androidx.lifecycle.LiveData;

import com.example.pokemon_app.data.models.pokemonInfo.PokemonInfoAPI;

public interface DetailRepository {
    public void fetchPokemonInfo(String namePoke);

    public LiveData<PokemonInfoAPI> getPokemonInfoLiveData();
    public LiveData<Boolean> getProgressBarLiveData();
    public LiveData<String> getToastLiveData();
    public void resetValuesLiveData();
    public void getDisposableToUnsubscribe();
}
