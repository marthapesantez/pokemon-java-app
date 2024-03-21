package com.example.pokemon_app.di;

import com.example.pokemon_app.data.repositoryImpl.DetailRepositoryImpl;
import com.example.pokemon_app.data.repositoryImpl.MainRepositoryImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, DatabaseModule.class})
public interface AppComponent {
    void injectMainRepository(MainRepositoryImpl mainRepository);

    void injectDetailRepository(DetailRepositoryImpl detailRepository);
}
