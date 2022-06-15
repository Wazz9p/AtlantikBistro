package com.wazz9p.domain.usecase.cart

import com.wazz9p.core.base.Result
import com.wazz9p.domain.model.cart.Cart
import com.wazz9p.domain.repository.cart.CartRepository
import javax.inject.Inject

class SendCart @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend fun execute(cart: Cart) = try {
        Result.Success(cartRepository.sendCart(cart))
    } catch (e: Exception) {
        Result.Error(e)
    }
}