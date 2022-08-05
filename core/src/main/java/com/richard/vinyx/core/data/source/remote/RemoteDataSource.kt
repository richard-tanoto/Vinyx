package com.richard.vinyx.core.data.source.remote

import com.richard.vinyx.core.data.source.remote.network.ApiResponse
import com.richard.vinyx.core.data.source.remote.network.ApiService
import com.richard.vinyx.core.data.source.remote.response.DetailResponse
import com.richard.vinyx.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getGames(): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            try {
                val response = apiService.getGames(null)
                val data = response.results
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGameDetail(id : Int) : Flow<ApiResponse<DetailResponse>> {
        return flow {
            try {
                val response = apiService.getGameDetail(id)
                emit(ApiResponse.Success(response))
            } catch (exception : Exception) {
                emit(ApiResponse.Error(exception.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}