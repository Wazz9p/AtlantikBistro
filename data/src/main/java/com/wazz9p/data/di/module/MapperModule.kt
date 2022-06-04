package com.wazz9p.data.di.module

import com.wazz9p.data.mappers.menu.CategoryEntityMapper
import com.wazz9p.data.mappers.menu.CategoryResponseMapper
import com.wazz9p.data.mappers.menu.MenuEntityMapper
import com.wazz9p.data.mappers.menu.MenuResponseMapper
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
}