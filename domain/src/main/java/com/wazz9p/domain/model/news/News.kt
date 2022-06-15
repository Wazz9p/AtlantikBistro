package com.wazz9p.domain.model.news

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class News(
    val id: Int,
    val title: String,
    val subtitle: String?,
    val text: String?,
    val imageUrl: String?
) : Parcelable
