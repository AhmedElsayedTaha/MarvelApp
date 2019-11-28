package com.marvelapp.Network;


import com.marvelapp.Models.Marvel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("characters")
    Single<Marvel> getMarvelData (@Query("ts") String ts,
                                  @Query("apikey") String apiKey,
                                  @Query("hash")String hashKey);

    @GET("characters/{characterID}")
    Single<Marvel> getAvengersDetails(@Path("characterID") int charID
                                        ,@Query("ts") String ts,
                                      @Query("apikey") String apiKey,
                                      @Query("hash")String hashKey);
}
