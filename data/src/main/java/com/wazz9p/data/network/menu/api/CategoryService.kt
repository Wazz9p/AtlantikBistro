package com.wazz9p.data.network.menu.api

import com.wazz9p.data.network.menu.response.CategoryResponse
import retrofit2.http.GET

interface CategoryService {
    @GET("/categories")
    suspend fun getCategories(): List<CategoryResponse>
}