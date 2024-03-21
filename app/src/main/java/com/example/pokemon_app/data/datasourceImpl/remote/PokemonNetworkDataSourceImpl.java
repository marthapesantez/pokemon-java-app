package com.example.pokemon_app.data.datasourceImpl.remote;

import com.example.pokemon_app.data.models.pokemonInfo.PokemonInfoAPI;
import com.example.pokemon_app.data.models.pokemonList.PokemonListAPI;
import com.example.pokemon_app.data.network.APIService;
import com.example.pokemon_app.domain.datasource.remote.PokemonNetworkDataSource;

import io.reactivex.rxjava3.core.Observable;

public class PokemonNetworkDataSourceImpl implements PokemonNetworkDataSource {

    private final APIService apiService;

    public PokemonNetworkDataSourceImpl(APIService apiService) {
        this.apiService = apiService;
    }


    @Override
    public Observable<PokemonListAPI> observableFetchPokemonList(int offset) {
        return apiService.fetchPokemonList(offset);
    }

    @Override
    public Observable<PokemonInfoAPI> observableFetchPokemonInfo(String name) {
        return apiService.fetchPokemonInfo(name);
    }
}
