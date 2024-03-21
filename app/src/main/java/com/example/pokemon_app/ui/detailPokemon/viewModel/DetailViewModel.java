package com.example.pokemon_app.ui.detailPokemon.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokemon_app.data.models.pokemonInfo.PokemonInfoAPI;
import com.example.pokemon_app.data.repositoryImpl.DetailRepositoryImpl;
import com.example.pokemon_app.domain.repository.DetailRepository;

import javax.inject.Inject;

public class DetailViewModel extends ViewModel {

    @Inject
    DetailRepository detailRepository;
    private final LiveData<PokemonInfoAPI> pokemonInfoLiveData;

    private final LiveData<Boolean> progressBarLiveData;

    private final LiveData<String> toastLiveData;

    public DetailViewModel() {
        detailRepository = new DetailRepositoryImpl();
        pokemonInfoLiveData = detailRepository.getPokemonInfoLiveData();
        progressBarLiveData = detailRepository.getProgressBarLiveData();
        toastLiveData = detailRepository.getToastLiveData();
    }

    public void resetValuesLiveData() {
        detailRepository.resetValuesLiveData();
    }

    public void fetchPokemonInfo(String namePoke) {
        detailRepository.fetchPokemonInfo(namePoke);
    }

    public void getDisposableToUnsubscribe(){
        detailRepository.getDisposableToUnsubscribe();
    }

    public LiveData<PokemonInfoAPI> getPokemonInfoLiveData() {
        return pokemonInfoLiveData;
    }

    public LiveData<Boolean> getProgressBarLiveData() {
        return progressBarLiveData;
    }

    public LiveData<String> getToastLiveData() {
        return toastLiveData;
    }
}

