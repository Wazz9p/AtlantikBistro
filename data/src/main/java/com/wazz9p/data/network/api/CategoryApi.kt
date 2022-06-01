package com.wazz9p.data.network.api

import com.wazz9p.data.network.response.CategoryResponse
import retrofit2.http.GET

interface CategoryApi {
    @GET("/categories")
    suspend fun getCategories(): List<CategoryResponse>
}