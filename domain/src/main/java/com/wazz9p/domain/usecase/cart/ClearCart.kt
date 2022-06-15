package com.wazz9p.domain.usecase.cart

import com.wazz9p.core.base.Result
import com.wazz9p.domain.repository.cart.CartRepository
import javax.inject.Inject

class ClearCart @Inject constructor(private val cartRepository: CartRepository) {
    suspend fun execute() = try {
        Result.Success(cartRepository.clearCart())
    } catch (e: Exception) {
        Result.Error(e)
    }
}