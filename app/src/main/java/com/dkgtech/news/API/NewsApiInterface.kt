package com.dkgtech.news.API

import com.dkgtech.news.model.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiInterface {
    @GET("everything")
    fun getEverythingNews(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsModel>
}