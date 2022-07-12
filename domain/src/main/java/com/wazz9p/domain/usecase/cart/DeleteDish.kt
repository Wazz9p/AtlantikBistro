package com.wazz9p.domain.usecase.cart

import com.wazz9p.core.base.Result
import com.wazz9p.domain.model.menu.Dish
import com.wazz9p.domain.repository.cart.CartRepository
import javax.inject.Inject

class DeleteDish @Inject constructor(private val cartRepository: CartRepository) {

    suspend fun execute(dish: Dish) = try {
        Result.Success(cartRepository.deleteDish(dish = dish))
    } catch (e: Exception) {
        Result.Error(e)
    }
}