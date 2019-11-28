package com.marvelapp.MvpImplementation;

import com.marvelapp.Interfaces.MarvelInterface;
import com.marvelapp.Models.Marvel;
import com.marvelapp.Network.APIClient;
import com.marvelapp.Network.APIService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MarvelInteractorImp implements MarvelInterface.MarvelInteractor {
    @Override
    public void getMarvelData(final MarvelListener marvelListener, String ts, String apiKey, String hash) {
        APIService apiService = APIClient.getRetrofitInstance().create(APIService.class);
        apiService.getMarvelData(ts,apiKey,hash)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Marvel>() {
                    @Override
                    public void onSuccess(Marvel marvel) {
                        marvelListener.onFinished(marvel.getData().getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        marvelListener.onFailed(e);
                    }
                });
    }

    @Override
    public void getDetails(final MarvelListener marvelListener, int id, String ts, String apiKey, String hash) {
        APIService apiService = APIClient.getRetrofitInstance().create(APIService.class);
        apiService.getAvengersDetails(id,ts,apiKey,hash)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Marvel>() {
                    @Override
                    public void onSuccess(Marvel value) {
                        marvelListener.onFinihshedGetDetails(value.getData().getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        marvelListener.onFailedGetDetails(e);
                    }
                });
    }
}
