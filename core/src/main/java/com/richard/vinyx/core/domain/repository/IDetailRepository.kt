package com.richard.vinyx.core.domain.repository

import com.richard.vinyx.core.data.Resource
import com.richard.vinyx.core.domain.model.Detail
import kotlinx.coroutines.flow.Flow

interface IDetailRepository {
    fun getGameDetail(id : Int) : Flow<Resource<Detail?>>
}