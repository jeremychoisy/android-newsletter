package com.mbds.newsletter.data.models

import androidx.room.*

@Entity
class Article(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val source: Source,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    @ColumnInfo(name= "url_to_image") val urlToImage: String,
    @ColumnInfo(name= "published_at") val publishedAt: String,
    val content: String?
) {}