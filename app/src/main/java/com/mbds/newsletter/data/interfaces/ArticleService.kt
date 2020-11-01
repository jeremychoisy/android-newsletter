package com.mbds.newsletter.data.interfaces

import com.mbds.newsletter.data.models.Article

interface ArticleService {
    fun getArticles(category: String): List<Article>
}