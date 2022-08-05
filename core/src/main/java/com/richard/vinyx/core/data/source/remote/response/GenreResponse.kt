package com.richard.vinyx.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GenreResponse(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,
)