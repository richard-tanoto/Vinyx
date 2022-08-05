package com.richard.vinyx.core.domain.usecase

import com.richard.vinyx.core.data.Resource
import com.richard.vinyx.core.domain.model.Detail
import com.richard.vinyx.core.domain.repository.IDetailRepository
import kotlinx.coroutines.flow.Flow

class DetailInteractor(private val detailRepository: IDetailRepository) : DetailUseCase {
    override fun getGameDetail(id: Int): Flow<Resource<Detail?>> = detailRepository.getGameDetail(id)
}