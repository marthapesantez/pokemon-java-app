package com.example.pokemon_app.domain.datasource.local;

import com.example.pokemon_app.data.models.pokemonInfo.PokemonInfoAPI;
import com.example.pokemon_app.data.models.pokemonList.ResultsResponse;

import java.util.List;

public interface PokemonLocalDataSource {
    void insertPokemonList(List<ResultsResponse> pokemonList);

    List<ResultsResponse> getPokemonList(int offset);

    void deleteAll();

    void insertPokemonInfo(PokemonInfoAPI pokemonInfoAPI);

    PokemonInfoAPI getPokemonInfo(String name);

    void deleteAllInfo();
}
