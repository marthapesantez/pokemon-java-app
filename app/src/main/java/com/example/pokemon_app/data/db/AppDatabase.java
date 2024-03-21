package com.example.pokemon_app.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.pokemon_app.data.db.dao.PokemonInfoDAO;
import com.example.pokemon_app.data.db.dao.PokemonListDAO;
import com.example.pokemon_app.data.models.pokemonInfo.PokemonInfoAPI;
import com.example.pokemon_app.data.models.pokemonList.ResultsResponse;

/**
 * TODO
 * implementar la bd
 */
@Database(entities = {ResultsResponse.class, PokemonInfoAPI.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract PokemonListDAO pokemonListDAO();

    public abstract PokemonInfoDAO pokemonInfoDAO();
}
