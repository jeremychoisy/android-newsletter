package com.mbds.newsletter.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mbds.newsletter.data.models.Source

class SourceConverter {
    var gson = Gson()

    @TypeConverter
    fun fromJson(data: String?): Source? {
        return gson.fromJson(data, Source::class.java)
    }

    @TypeConverter
    fun sourceObjectToString(source: Source?): String? {
        return gson.toJson(source)
    }
}