package com.example.pokemon_app.data.network;

import com.example.pokemon_app.data.models.pokemonInfo.PokemonInfoAPI;
import com.example.pokemon_app.data.models.pokemonList.PokemonListAPI;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("pokemon?limit=40")
    Observable<PokemonListAPI> fetchPokemonList(@Query("offset") int offset);

    @GET("pokemon/{name}")
    Observable<PokemonInfoAPI> fetchPokemonInfo(@Path("name") String name);
}
