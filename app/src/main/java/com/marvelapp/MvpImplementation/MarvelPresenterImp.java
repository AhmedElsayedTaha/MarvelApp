package com.marvelapp.MvpImplementation;

import com.marvelapp.Interfaces.MarvelInterface;
import com.marvelapp.Models.Result;

import java.util.List;

public class MarvelPresenterImp implements MarvelInterface.MarvelInteractor.MarvelListener,MarvelInterface.MarvelPresenter {

    private MarvelInterface.MarvelInteractor marvelInteractor;
    private MarvelInterface.MarvelView marvelView;

    public MarvelPresenterImp(MarvelInterface.MarvelInteractor marvelInteractor, MarvelInterface.MarvelView marvelView) {
        this.marvelInteractor = marvelInteractor;
        this.marvelView = marvelView;
    }

    @Override
    public void requestMarvelData(String ts, String apiKey, String hash) {
        marvelInteractor.getMarvelData(this,ts,apiKey,hash);
    }

    @Override
    public void requestDetails(int id, String ts, String apiKey, String hash) {
        marvelInteractor.getDetails(this,id,ts,apiKey,hash);
    }

    @Override
    public void onFinished(List<Result> results) {
        if(marvelView!=null)
            marvelView.dataFetchedSuccessfully(results);
    }

    @Override
    public void onFailed(Throwable t) {
        if(marvelView!=null)
            marvelView.failedToGetMarvelObject(t);
    }

    @Override
    public void onFinihshedGetDetails(List<Result> detailsResult) {
        if(marvelView!=null)
            marvelView.detailsDataFetchedDetails(detailsResult);
    }

    @Override
    public void onFailedGetDetails(Throwable t) {
        if(marvelView!=null)
            marvelView.failedToGetDetails(t);
    }
}
