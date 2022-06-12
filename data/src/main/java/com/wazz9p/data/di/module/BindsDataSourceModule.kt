package com.wazz9p.data.di.module

import com.wazz9p.data.database.menu.CategoryLocalDataSource
import com.wazz9p.data.database.menu.MenuLocalDataSource
import com.wazz9p.data.database.menu.localDataSourceImpl.CategoryRoomDataSource
import com.wazz9p.data.database.menu.localDataSourceImpl.MenuRoomDataSource
import com.wazz9p.data.database.news.NewsLocalDataSource
import com.wazz9p.data.database.news.localDataSourceImpl.NewsRoomDataSource
import com.wazz9p.data.database.review.ReviewLocalDataSource
import com.wazz9p.data.database.review.localDataSourceImpl.ReviewRoomDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface BindsDataSourceModule {

    @Binds
    fun bindsCategoryLocalDataSource(roomDataSource: CategoryRoomDataSource): CategoryLocalDataSource

    @Binds
    fun bindsMenuLocalDataSource(roomDataSource: MenuRoomDataSource): MenuLocalDataSource

    @Binds
    fun bindsNewsLocalDataSource(roomDataSource: NewsRoomDataSource): NewsLocalDataSource

    @Binds
    fun bindsReviewLocalDataSource(roomDataSource: ReviewRoomDataSource): ReviewLocalDataSource
}