package com.marvelapp.Interfaces;

import com.marvelapp.Models.Marvel;
import com.marvelapp.Models.Result;

import java.util.List;

public interface MarvelInterface {
    interface MarvelPresenter{
        void requestMarvelData(String ts,String apiKey,String hash);
        //Get Details
        void requestDetails(int id,String ts,String apiKey,String hash);
    }

    interface MarvelInteractor{
        interface MarvelListener{
            void onFinished(List<Result> results);
            void onFailed(Throwable t);
            //Details
            void onFinihshedGetDetails(List<Result> detailsResult);
            void onFailedGetDetails(Throwable t);
        }
        void getMarvelData(MarvelListener marvelListener,String ts,String apiKey,String hash);
        //Get details
        void getDetails(MarvelListener marvelListener,int id,String ts,String apiKey,String hash);
    }

    interface MarvelView{
        void dataFetchedSuccessfully(List<Result> results);
        void failedToGetMarvelObject(Throwable t);
        //Details
        void detailsDataFetchedDetails(List<Result> resultList);
        void failedToGetDetails(Throwable t);
    }
}
