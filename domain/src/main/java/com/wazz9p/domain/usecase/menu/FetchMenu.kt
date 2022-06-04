package com.wazz9p.domain.usecase.menu

import javax.inject.Inject
import com.wazz9p.core.base.Result
import com.wazz9p.domain.model.menu.Dish
import com.wazz9p.domain.repository.menu.MenuRepository
import java.io.IOException

class FetchMenu @Inject constructor(private val menuRepository: MenuRepository) {

    suspend fun execute(categoryId: Int): Result<List<Dish>> = try {
        Result.Success(data = menuRepository.getMenu(categoryId))
    } catch (e: IOException) {
        Result.Error(e)
    }
}