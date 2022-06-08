package com.wazz9p.domain.usecase.menu

import com.wazz9p.domain.model.menu.Dish
import com.wazz9p.core.base.Result
import com.wazz9p.domain.repository.menu.MenuRepository
import java.io.IOException
import javax.inject.Inject

class FetchDish @Inject constructor(private val menuRepository: MenuRepository) {

    suspend fun execute(dishId: Int): Result<Dish> = try {
        Result.Success(data = menuRepository.getDish(dishId))
    } catch (e: IOException) {
        Result.Error(e)
    }
}