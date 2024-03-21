package com.example.pokemon_app.data.db.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pokemon_app.data.models.pokemonInfo.PokemonInfoAPI;

@Dao
public interface PokemonInfoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPokemonInfo(PokemonInfoAPI pokemonInfoAPI);

    @Query("SELECT * FROM PokemonInfo WHERE `name` = :name")
    PokemonInfoAPI getPokemonInfo(String name);

    @Query("DELETE FROM PokemonInfo")
    void deleteAllInfo();
}

