package com.wazz9p.data.network.review.response

import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("message") val message: String,
    @SerializedName("phone") val phone: String?,
    @SerializedName("adminMessage") val adminMessage: String?
)
