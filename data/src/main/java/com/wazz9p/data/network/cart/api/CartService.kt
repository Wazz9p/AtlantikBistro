package com.wazz9p.data.network.cart.api

import com.wazz9p.data.network.cart.CartResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface CartService {

    @POST("/calls/order")
    suspend fun sendCart(@Body data: CartResponse)
}