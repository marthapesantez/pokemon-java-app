package com.example.pokemon_app.data.repositoryImpl;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pokemon_app.App;
import com.example.pokemon_app.data.models.pokemonList.PokemonListAPI;
import com.example.pokemon_app.data.models.pokemonList.ResultsResponse;
import com.example.pokemon_app.domain.datasource.local.PokemonLocalDataSource;
import com.example.pokemon_app.domain.datasource.remote.PokemonNetworkDataSource;
import com.example.pokemon_app.domain.repository.MainRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainRepositoryImpl implements MainRepository {

    @Inject
    PokemonNetworkDataSource pokemonNetworkDataSource;
    @Inject
    PokemonLocalDataSource pokemonLocalDataSource;
    private final List<ResultsResponse> pokemonList = new ArrayList<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final  MutableLiveData<List<ResultsResponse>> pokemonListLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> progressBarLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> swipeRefreshLayoutLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> toastLiveData = new MutableLiveData<>();



    public MainRepositoryImpl() {
        getInjection();
    }



    public void fetchPokemonList(int offset) {
        progressBarLiveData.setValue(true);

        Observable<PokemonListAPI> pokemonListAPIObservable = pokemonNetworkDataSource.observableFetchPokemonList(offset);
        DisposableObserver<PokemonListAPI> pokemonListAPIObserver = getPokemonListAPIObserver(offset);

        Disposable disposableFetchData = pokemonListAPIObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(pokemonListAPIObserver);

        compositeDisposable.add(disposableFetchData);
    }

    private DisposableObserver<PokemonListAPI> getPokemonListAPIObserver(int offset) {
        return new DisposableObserver<PokemonListAPI>() {
            @Override
            public void onNext(@NonNull PokemonListAPI pokemonListAPI) {
                onResponseSuccess(pokemonListAPI, offset);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                onResponseFail(e, offset);
            }

            @Override
            public void onComplete() {

            }
        };
    }

    private void onResponseSuccess(PokemonListAPI pokemonListAPI, int offset) {
        pokemonList.clear();

        List<ResultsResponse> resultsList = pokemonListAPI.getResults();

        for (int i = 0; i < resultsList.size(); i++) {
            ResultsResponse result = resultsList.get(i);

            String namePoke = result.getName();

            String urlPoke = result.getUrl().replaceFirst(".$", "").substring(33);

            pokemonList.add(new ResultsResponse(offset, namePoke, urlPoke));
        }

        progressBarLiveData.setValue(false);
        pokemonListLiveData.setValue(pokemonList);
        swipeRefreshLayoutLiveData.setValue(true);
        onInsertPokemonListIntoDatabase(pokemonList);
    }
   private void onResponseFail(Throwable e, int offset) {
        progressBarLiveData.setValue(false);

        if (pokemonLocalDataSource.getPokemonList(offset).isEmpty()) {
            toastLiveData.setValue(e.getMessage());
        } else {
            pokemonListLiveData.setValue(pokemonLocalDataSource.getPokemonList(offset));
            swipeRefreshLayoutLiveData.setValue(true);
            toastLiveData.setValue("");
        }
    }

    private void onInsertPokemonListIntoDatabase(List<ResultsResponse> pokemonList) {
        Observable<List<ResultsResponse>> observable = Observable.just(pokemonList);

        Disposable disposableInsertData = observable
                .doOnNext(resultsResponses -> pokemonLocalDataSource.insertPokemonList(resultsResponses))
                .subscribeOn(Schedulers.io())
                .subscribe();

        compositeDisposable.add(disposableInsertData);
    }

    private void getInjection() {
        App.getAppComponent().injectMainRepository(this);
    }

    public LiveData<List<ResultsResponse>> getPokemonListLiveData() {
        return pokemonListLiveData;
    }

    public LiveData<Boolean> getProgressBarLiveData() {
        return progressBarLiveData;
    }

    public  LiveData<Boolean> getSwipeRefreshLayoutLiveData() {
        return swipeRefreshLayoutLiveData;
    }

    public  LiveData<String> getToastLiveData() {
        return toastLiveData;
    }

    public void resetValuesLiveData() {
        pokemonListLiveData.postValue(null);
        progressBarLiveData.postValue(null);
        swipeRefreshLayoutLiveData.postValue(null);
        toastLiveData.postValue(null);
    }

    public void getDisposableToUnsubscribe() {
        compositeDisposable.dispose();
    }
}
