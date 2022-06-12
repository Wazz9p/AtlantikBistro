package com.wazz9p.data.di.module

import com.wazz9p.data.mappers.menu.CategoryEntityMapper
import com.wazz9p.data.mappers.menu.CategoryResponseMapper
import com.wazz9p.data.mappers.menu.MenuEntityMapper
import com.wazz9p.data.mappers.menu.MenuResponseMapper
import com.wazz9p.data.mappers.news.NewsEntityMapper
import com.wazz9p.data.mappers.news.NewsResponseMapper
import com.wazz9p.data.mappers.review.ReviewEntityMapper
import com.wazz9p.data.mappers.review.ReviewResponseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class MapperModule {

    @Provides
    fun provideCategoryEntityMapper(): CategoryEntityMapper = CategoryEntityMapper()

    @Provides
    fun provideCategoryResponseMapper(): CategoryResponseMapper = CategoryResponseMapper()

    @Provides
    fun provideMenuEntityMapper(): MenuEntityMapper = MenuEntityMapper()

    @Provides
    fun provideMenuResponseMapper(): MenuResponseMapper = MenuResponseMapper()

    @Provides
    fun provideNewsEntityMapper(): NewsEntityMapper = NewsEntityMapper()

    @Provides
    fun provideNewsResponseMapper(): NewsResponseMapper = NewsResponseMapper()

    @Provides
    fun provideReviewEntityMapper(): ReviewEntityMapper = ReviewEntityMapper()

    @Provides
    fun provideReviewResponseMapper(): ReviewResponseMapper = ReviewResponseMapper()
}