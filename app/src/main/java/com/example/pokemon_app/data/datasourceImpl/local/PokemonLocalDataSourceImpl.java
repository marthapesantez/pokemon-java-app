package com.example.pokemon_app.data.datasourceImpl.local;

import com.example.pokemon_app.data.db.dao.PokemonInfoDAO;
import com.example.pokemon_app.data.db.dao.PokemonListDAO;
import com.example.pokemon_app.data.models.pokemonInfo.PokemonInfoAPI;
import com.example.pokemon_app.data.models.pokemonList.ResultsResponse;
import com.example.pokemon_app.domain.datasource.local.PokemonLocalDataSource;

import java.util.List;

public class PokemonLocalDataSourceImpl implements PokemonLocalDataSource {

    private final PokemonInfoDAO pokemonInfoDAO;

    private final PokemonListDAO pokemonListDAO;

    public PokemonLocalDataSourceImpl(PokemonInfoDAO pokemonInfoDAO, PokemonListDAO pokemonListDAO) {
        this.pokemonInfoDAO = pokemonInfoDAO;
        this.pokemonListDAO = pokemonListDAO;
    }


    @Override
    public void insertPokemonList(List<ResultsResponse> pokemonList) {
        pokemonListDAO.insertPokemonList(pokemonList);
    }

    @Override
    public List<ResultsResponse> getPokemonList(int offset) {
        return pokemonListDAO.getPokemonList(offset);
    }

    @Override
    public void deleteAll() {
        pokemonListDAO.deleteAll();
    }

    @Override
    public void insertPokemonInfo(PokemonInfoAPI pokemonInfoAPI) {
        pokemonInfoDAO.insertPokemonInfo(pokemonInfoAPI);
    }

    @Override
    public PokemonInfoAPI getPokemonInfo(String name) {
        return pokemonInfoDAO.getPokemonInfo(name);
    }

    @Override
    public void deleteAllInfo() {
        pokemonInfoDAO.deleteAllInfo();
    }
}
