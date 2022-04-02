package com.example.newsApp.model

data class NewsResponse(
    val status: String,
    val articles: List<News>
)