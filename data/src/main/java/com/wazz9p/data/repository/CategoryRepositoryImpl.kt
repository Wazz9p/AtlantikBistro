package com.wazz9p.data.repository

import com.wazz9p.data.database.menu.CategoryLocalDataSource
import com.wazz9p.data.network.menu.CategoryRemoteDataSource
import com.wazz9p.domain.model.menu.Category
import com.wazz9p.domain.repository.menu.CategoryRepository
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    private val remoteDataSource: CategoryRemoteDataSource,
    private val localDataSource: CategoryLocalDataSource
) : CategoryRepository {

    override suspend fun getCategories(): List<Category> {
        return try {
            val response = remoteDataSource.getCategories()
            localDataSource.saveCategories(response)
            response
        } catch (e: UnknownHostException) {
            localDataSource.getCategories()
        }
    }
}