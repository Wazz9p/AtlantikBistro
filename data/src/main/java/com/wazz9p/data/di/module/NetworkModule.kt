package com.wazz9p.data.di.module

import com.google.gson.GsonBuilder
import com.wazz9p.data.mappers.menu.CategoryResponseMapper
import com.wazz9p.data.network.menu.api.CategoryService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
class NetworkModule {
    @Provides
    fun provideCategoryResponseMapper(): CategoryResponseMapper = CategoryResponseMapper()

    @Provides
    fun provideProductionCategoryService(): CategoryService {

        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://restarauntbistro-obed.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClient)
            .build()
        return retrofit.create()
    }
}