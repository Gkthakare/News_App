package com.example.newsApp.api

import com.example.newsApp.model.News
import com.example.newsApp.model.NewsResponse
import retrofit2.http.GET

interface NewsService {
    @GET("everything?q=tesla&from=2022-03-02&sortBy=publishedAt&apiKey=12b0cb6c831842bf924747df98723d1b")
    suspend fun getHeadline(): NewsResponse
}