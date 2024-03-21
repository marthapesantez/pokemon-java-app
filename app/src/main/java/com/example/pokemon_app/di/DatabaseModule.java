package com.example.pokemon_app.di;

import android.app.Application;

import androidx.room.Room;

import com.example.pokemon_app.data.datasourceImpl.local.PokemonLocalDataSourceImpl;
import com.example.pokemon_app.data.db.AppDatabase;
import com.example.pokemon_app.data.db.dao.PokemonInfoDAO;
import com.example.pokemon_app.data.db.dao.PokemonListDAO;
import com.example.pokemon_app.domain.datasource.local.PokemonLocalDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    private final Application application;

    public DatabaseModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "PokemonDatabase")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }


    @Provides
    @Singleton
    PokemonListDAO providePokemonListDAO(AppDatabase appDatabase) {
        return appDatabase.pokemonListDAO();
    }

    @Provides
    @Singleton
    PokemonInfoDAO providePokemonInfoDAO(AppDatabase appDatabase) {
        return appDatabase.pokemonInfoDAO();
    }

    @Provides
    @Singleton
    PokemonLocalDataSource provideLocalDataSource(PokemonInfoDAO pokemonInfoDAO, PokemonListDAO pokemonListDAO){
        return new PokemonLocalDataSourceImpl(pokemonInfoDAO, pokemonListDAO);
    }
}

