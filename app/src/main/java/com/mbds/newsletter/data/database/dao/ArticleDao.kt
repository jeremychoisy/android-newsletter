package com.mbds.newsletter.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mbds.newsletter.data.models.Article

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getAll(): LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article)

    @Delete
    fun delete(article: Article)
}