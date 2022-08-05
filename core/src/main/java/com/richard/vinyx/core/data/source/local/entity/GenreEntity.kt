package com.richard.vinyx.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class GenreEntity (

    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,
)
