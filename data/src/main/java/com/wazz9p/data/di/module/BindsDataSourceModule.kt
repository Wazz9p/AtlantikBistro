package com.wazz9p.data.di.module

import com.wazz9p.data.database.cart.CartLocalDataSource
import com.wazz9p.data.database.cart.localDataSourceImpl.CartRoomDataSource
import com.wazz9p.data.database.menu.CategoryLocalDataSource
import com.wazz9p.data.database.menu.MenuLocalDataSource
import com.wazz9p.data.database.menu.localDataSourceImpl.CategoryRoomDataSource
import com.wazz9p.data.database.menu.localDataSourceImpl.MenuRoomDataSource
import com.wazz9p.data.database.news.NewsLocalDataSource
import com.wazz9p.data.database.news.localDataSourceImpl.NewsRoomDataSource
import com.wazz9p.data.database.review.ReviewLocalDataSource
import com.wazz9p.data.database.review.localDataSourceImpl.ReviewRoomDataSource
import com.wazz9p.data.network.cart.CartRemoteDataSource
import com.wazz9p.data.network.cart.CartRetrofitDataSource
import com.wazz9p.data.network.review.ReviewRemoteDataSource
import com.wazz9p.data.network.review.remoteDataSourceImpl.ReviewRetrofitDataSource
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

    @Binds
    fun bindReviewRemoteDataSource(retrofitDataSource: ReviewRetrofitDataSource): ReviewRemoteDataSource

    @Binds
    fun binsCartLocalDataSource(roomDataSource: CartRoomDataSource): CartLocalDataSource

    @Binds
    fun bindsCartRemoteDataSource(retrofitDataSource: CartRetrofitDataSource): CartRemoteDataSource
}