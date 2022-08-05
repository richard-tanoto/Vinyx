package com.richard.vinyx.core.data.source.remote.network

import com.richard.vinyx.core.data.source.remote.response.DetailResponse
import com.richard.vinyx.core.data.source.remote.response.ListGameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getGames(
        @Query("search") search: String?,
    ): ListGameResponse

    @GET("games/{id}")
    suspend fun getGameDetail(
        @Path("id") id: Int,
    ) : DetailResponse

}