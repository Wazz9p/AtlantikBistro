package com.wazz9p.data.network.review.response

import com.google.gson.annotations.SerializedName

data class SendReview(
    @SerializedName("phone") val phone: String,
    @SerializedName("message") val message: String,
    @SerializedName("name") val name: String
)