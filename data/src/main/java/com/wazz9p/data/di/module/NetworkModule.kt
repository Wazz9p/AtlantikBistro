package com.wazz9p.data.di.module

import com.google.gson.GsonBuilder
import com.wazz9p.data.mappers.menu.CategoryResponseMapper
import com.wazz9p.data.network.menu.api.CategoryService
import com.wazz9p.data.network.menu.api.MenuService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

        val okHttpClient = OkHttpClient.Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl("https://restarauntbistro-obed.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideCategoryService(retrofit: Retrofit): CategoryService =
        retrofit.create(CategoryService::class.java)

    @Singleton
    @Provides
    fun provideMenuService(retrofit: Retrofit): MenuService =
        retrofit.create(MenuService::class.java)
}