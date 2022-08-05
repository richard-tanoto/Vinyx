package com.richard.vinyx.core.data.source.local.entity

import androidx.room.*

@Entity(tableName = "games")
data class GameEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name = "ratings_count")
    var ratingsCount: Int,

    @ColumnInfo(name = "genres")
    var genres: List<GenreEntity>,

    @ColumnInfo(name = "background_image")
    val backgroundImage: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
)
