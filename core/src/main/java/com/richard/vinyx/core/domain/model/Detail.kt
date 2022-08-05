package com.richard.vinyx.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Detail(
    val rating: Double,
    val id: Int,
    val ratingsCount: Int,
    val backgroundImage: String,
    val name: String,
    val descriptionRaw: String,
    val genres: List<Genre>,
) : Parcelable
