package com.example.pokemon_app.data.models.pokemonList;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonListAPI {

    @SerializedName("results")
    public List<ResultsResponse> results;

    public List<ResultsResponse> getResults() {
        return results;
    }
}

