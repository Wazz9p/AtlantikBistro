package com.wazz9p.domain.usecase.cart

import com.wazz9p.core.base.Result
import com.wazz9p.domain.model.menu.Dish
import com.wazz9p.domain.repository.cart.CartRepository
import java.io.IOException
import javax.inject.Inject

class FetchCart @Inject constructor(private val cartRepository: CartRepository) {

    suspend fun execute(): Result<List<Dish>> = try {
        Result.Success(data = cartRepository.getCartDishList())
    } catch (e: IOException) {
        Result.Error(e)
    }
}