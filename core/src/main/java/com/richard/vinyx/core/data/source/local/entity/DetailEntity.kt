package com.richard.vinyx.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail")
data class DetailEntity(

    @ColumnInfo("rating")
    var rating: Double,

    @PrimaryKey
    @ColumnInfo("id")
    var id: Int,

    @ColumnInfo("ratings_count")
    var ratingsCount: Int,

    @ColumnInfo("backgroundImage")
    var backgroundImage: String,

    @ColumnInfo("name")
    var name: String,

    @ColumnInfo("description_raw")
    var descriptionRaw: String,

    @ColumnInfo("genres")
    var genres: List<GenreEntity>,
)
