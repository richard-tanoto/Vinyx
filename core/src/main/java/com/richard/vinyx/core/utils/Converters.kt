package com.richard.vinyx.core.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.richard.vinyx.core.data.source.local.entity.GenreEntity

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromGenres(value: List<GenreEntity>) : String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toGenres(value: String) : List<GenreEntity> {
        return gson.fromJson(value, object: TypeToken<List<GenreEntity>>(){}.type)
    }
}