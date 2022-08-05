package com.richard.vinyx.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("genres")
    val genres: List<GenreResponse>,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("ratings_count")
    val ratingsCount: Int,

    @field:SerializedName("background_image")
    val backgroundImage: String,

    @field:SerializedName("name")
    val name: String,

)
