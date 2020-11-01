package com.mbds.newsletter.data.models

data class ArticlesResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>) {}