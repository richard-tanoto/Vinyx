package com.richard.vinyx.core.domain.usecase

import com.richard.vinyx.core.data.Resource
import com.richard.vinyx.core.domain.model.Detail
import kotlinx.coroutines.flow.Flow

interface DetailUseCase {
    fun getGameDetail(id: Int): Flow<Resource<Detail?>>
}