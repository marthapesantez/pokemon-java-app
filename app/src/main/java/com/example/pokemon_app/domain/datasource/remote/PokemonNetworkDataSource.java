package com.example.pokemon_app.domain.datasource.remote;

import com.example.pokemon_app.data.models.pokemonInfo.PokemonInfoAPI;
import com.example.pokemon_app.data.models.pokemonList.PokemonListAPI;

import io.reactivex.rxjava3.core.Observable;

public interface PokemonNetworkDataSource {
    Observable<PokemonListAPI> observableFetchPokemonList(int offset);

    Observable<PokemonInfoAPI> observableFetchPokemonInfo(String name);

}
