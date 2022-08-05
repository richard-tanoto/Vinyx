package com.richard.vinyx.core.utils

import com.richard.vinyx.core.data.source.local.entity.DetailEntity
import com.richard.vinyx.core.data.source.local.entity.GameEntity
import com.richard.vinyx.core.data.source.local.entity.GenreEntity
import com.richard.vinyx.core.data.source.remote.response.DetailResponse
import com.richard.vinyx.core.data.source.remote.response.GameResponse
import com.richard.vinyx.core.data.source.remote.response.GenreResponse
import com.richard.vinyx.core.domain.model.Detail
import com.richard.vinyx.core.domain.model.Game
import com.richard.vinyx.core.domain.model.Genre

object DataMapper {

    fun mapResponsesToEntities(input: List<GameResponse>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                id = it.id,
                name = it.name,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                ratingsCount = it.ratingsCount,
                genres = mapGenreResponsesToEntities(it.genres),
                isFavorite = false
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapDetailResponseToEntity(input: DetailResponse): DetailEntity = DetailEntity(
        id = input.id,
        name = input.name,
        backgroundImage = input.backgroundImage,
        rating = input.rating,
        ratingsCount = input.ratingsCount,
        genres = mapGenreResponsesToEntities(input.genres),
        descriptionRaw = input.descriptionRaw
    )

    private fun mapGenreResponsesToEntities(input: List<GenreResponse>): List<GenreEntity> {
        val genreList = ArrayList<GenreEntity>()
        input.map {
            val genre = GenreEntity(
                id = it.id,
                name = it.name
            )
            genreList.add(genre)
        }
        return genreList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                id = it.id,
                name = it.name,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                ratingsCount = it.ratingsCount,
                genres = mapGenreEntitiesToDomain(it.genres),
                isFavorite = it.isFavorite
            )
        }

    private fun mapGenreEntitiesToDomain(input: List<GenreEntity>): List<Genre> =
        input.map {
            Genre(
                id = it.id,
                name = it.name
            )
        }

    fun mapDetailEntitiesToDomain(input: DetailEntity?): Detail? = if (input == null) null else Detail(
        id = input.id,
        name = input.name,
        backgroundImage = input.backgroundImage,
        rating = input.rating,
        ratingsCount = input.ratingsCount,
        genres = mapGenreEntitiesToDomain(input.genres),
        descriptionRaw = input.descriptionRaw
    )

    fun mapDomainToEntity(input: Game) = GameEntity(
        id = input.id,
        name = input.name,
        backgroundImage = input.backgroundImage,
        rating = input.rating,
        ratingsCount = input.ratingsCount,
        genres = mapGenreDomainToEntity(input.genres),
        isFavorite = input.isFavorite
    )

    private fun mapGenreDomainToEntity(input: List<Genre>): List<GenreEntity> {
        val genreList = ArrayList<GenreEntity>()
        input.map {
            val genre = GenreEntity(
                id = it.id,
                name = it.name
            )
            genreList.add(genre)
        }
        return genreList
    }

}