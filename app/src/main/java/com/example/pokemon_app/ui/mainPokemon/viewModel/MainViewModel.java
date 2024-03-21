package com.example.pokemon_app.ui.mainPokemon.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokemon_app.data.models.pokemonList.ResultsResponse;
import com.example.pokemon_app.data.repositoryImpl.MainRepositoryImpl;
import com.example.pokemon_app.domain.repository.MainRepository;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {


    @Inject
    MainRepository mainRepository;

    private final LiveData<List<ResultsResponse>> pokemonListLiveData;

    private final LiveData<Boolean> progressBarLiveData;

    private final LiveData<Boolean> swipeRefreshLayoutLiveData;

    private final LiveData<String> toastLiveData;

    public MainViewModel() {
        mainRepository = new MainRepositoryImpl();
        pokemonListLiveData = mainRepository.getPokemonListLiveData();
        progressBarLiveData = mainRepository.getProgressBarLiveData();
        swipeRefreshLayoutLiveData = mainRepository.getSwipeRefreshLayoutLiveData();
        toastLiveData = mainRepository.getToastLiveData();
    }


    public void fetchPokemonList(int offset) {
        mainRepository.fetchPokemonList(offset);
    }
    public void resetValuesLiveData(){
        mainRepository.resetValuesLiveData();
    }

    public void getDisposableToUnsubscribe(){
        mainRepository.getDisposableToUnsubscribe();
    }



    public LiveData<List<ResultsResponse>> getPokemonListLiveData() {
        return pokemonListLiveData;
    }

    public LiveData<Boolean> getProgressBarLiveData() {
        return progressBarLiveData;
    }

    public LiveData<Boolean> getSwipeRefreshLayoutLiveData() {
        return swipeRefreshLayoutLiveData;
    }

    public LiveData<String> getToastLiveData() {
        return toastLiveData;
    }
}
