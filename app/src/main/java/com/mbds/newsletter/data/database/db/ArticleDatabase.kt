package com.mbds.newsletter.data.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mbds.newsletter.data.database.converters.SourceConverter
import com.mbds.newsletter.data.database.dao.ArticleDao
import com.mbds.newsletter.data.models.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(SourceConverter::class)
abstract class ArticleDatabase: RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object{
        private var INSTANCE: ArticleDatabase? = null
        fun getInstance(context: Context): ArticleDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                        context,
                        ArticleDatabase::class.java,
                        "articleDB")
                        .build()
            }

            return INSTANCE as ArticleDatabase
        }
    }
}