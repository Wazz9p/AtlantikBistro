package com.wazz9p.data.di.module

import com.wazz9p.data.repository.*
import com.wazz9p.domain.repository.cart.CartRepository
import com.wazz9p.domain.repository.menu.CategoryRepository
import com.wazz9p.domain.repository.menu.MenuRepository
import com.wazz9p.domain.repository.news.NewsRepository
import com.wazz9p.domain.repository.review.ReviewRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface BindsRepositoryModule {

    @Binds
    fun bindCategoryRepository(repository: CategoryRepositoryImpl): CategoryRepository

    @Binds
    fun bindMenuRepository(repository: MenuRepositoryImpl): MenuRepository

    @Binds
    fun bindNewsRepository(repository: NewsRepositoryImpl): NewsRepository

    @Binds
    fun bindReviewRepository(repository: ReviewRepositoryImpl): ReviewRepository

    @Binds
    fun bindCartRepository(repository: CartRepositoryImpl): CartRepository
}