package com.wazz9p.data.network.menu.response

import com.google.gson.annotations.SerializedName

data class MenuResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: String?,
    @SerializedName("price") val price: String,
    @SerializedName("imageRef") val imageRef: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("categoryId") val categoryId: Int
)