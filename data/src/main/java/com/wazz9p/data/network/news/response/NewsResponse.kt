package com.wazz9p.data.network.news.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("subTitle") var subTitle: String?,
    @SerializedName("imageRef") var imageRef: String?,
    @SerializedName("description") var description: String?
)
