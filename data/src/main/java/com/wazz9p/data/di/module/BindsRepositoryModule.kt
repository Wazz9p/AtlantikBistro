package com.wazz9p.data.di.module

import com.wazz9p.data.repository.CategoryRepositoryImpl
import com.wazz9p.domain.repository.menu.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface BindsRepositoryModule {

    @Binds
    fun bindCategoryRepository(repository: CategoryRepositoryImpl): CategoryRepository


}