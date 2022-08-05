package com.richard.vinyx.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("ratings_count")
	val ratingsCount: Int,

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description_raw")
	val descriptionRaw: String,

	@field:SerializedName("genres")
	val genres: List<GenreResponse>,

)
