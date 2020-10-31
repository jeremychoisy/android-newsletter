package com.mbds.newsletter.data.models

import com.mbds.newsletter.data.models.Article

data class ArticlesResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>) {}