package com.mbds.newsletter.data.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Editor(
        val id: String,
        val name: String,
        val description: String,
        val url: String,
        val category: String,
        val language: String,
        val country: String
) {}
