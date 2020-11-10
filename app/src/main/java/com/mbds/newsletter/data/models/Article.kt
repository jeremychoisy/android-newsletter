package com.mbds.newsletter.data.models

import androidx.room.*

@Entity(primaryKeys = ["source", "title", "description", "published_at"])
class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    @ColumnInfo(name= "url_to_image") val urlToImage: String,
    @ColumnInfo(name= "published_at") val publishedAt: String,
    val content: String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Article

        if(!(source == other.source
            && author == other.author
            && title == other.title
            && description == other.description
            && url == other.url
            && urlToImage == other.urlToImage
            && publishedAt == other.publishedAt
            && content == other.content)) return false

        return true
    }
}