package com.richard.vinyx.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: Int,
    val name: String,
    val rating: Double,
    val ratingsCount: Int,
    val genres: List<Genre>,
    val backgroundImage: String,
    val isFavorite: Boolean = false,
): Parcelable