package com.mbds.newsletter.interfaces

import androidx.lifecycle.LiveData
import com.mbds.newsletter.data.models.Article

interface ArticleCallback {
    fun onClick(article: Article)
    fun getFavoriteArticles(): LiveData<List<Article>>

    // Provide a default implementation in order to be able to omit those functions if not necessary
    fun addFavoriteArticle(article: Article) {}
    fun removeFavoriteArticle(article: Article) {}
}