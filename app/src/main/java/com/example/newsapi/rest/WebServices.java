package com.example.newsapi.rest;


import com.example.newsapi.Model.Articles.ArticlesResponse;
import com.example.newsapi.Model.Sources.SourcesResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebServices {

    @GET("sources")
    Single<SourcesResponse> getSources(@Query("category") String category,
                                       @Query("language") String language,
                                       @Query("country") String country,
                                       @Query("apiKey") String apiKey);

    @GET("everything")
    Single<ArticlesResponse> getNews(@Query("sources") String sources,
                                     @Query("language") String language,
                                     @Query("apiKey") String apiKey);

    //@Query("q") String keyword,
    // @Query("language") String language,
    // @Query("sortBy") String sortBy,

}
