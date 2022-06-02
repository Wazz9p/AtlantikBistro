package com.wazz9p.data.network.response

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("imageRef") val imageRef: String?,
    @field:SerializedName("name") val name: String
)
