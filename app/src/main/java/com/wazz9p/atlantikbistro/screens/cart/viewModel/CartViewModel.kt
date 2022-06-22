package com.wazz9p.atlantikbistro.screens.cart.viewModel

import androidx.lifecycle.viewModelScope
import com.wazz9p.atlantikbistro.screens.news.newsList.viewModel.NewsViewModel
import com.wazz9p.core.base.BaseAction
import com.wazz9p.core.base.BaseViewModel
import com.wazz9p.core.base.BaseViewState
import com.wazz9p.core.base.Result
import com.wazz9p.domain.model.cart.Cart
import com.wazz9p.domain.model.menu.Dish
import com.wazz9p.domain.model.menu.toCartDish
import com.wazz9p.domain.usecase.cart.ClearCart
import com.wazz9p.domain.usecase.cart.FetchCart
import com.wazz9p.domain.usecase.cart.SendCart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CartViewModel @Inject constructor(
    private val fetchCart: FetchCart,
    private val sendCart: SendCart,
    private val clearCart: ClearCart
) : BaseViewModel<CartViewModel.ViewState, CartViewModel.Action>(ViewState()) {

    override fun onLoadData() {
        getCart()
    }

    private fun getCart() {
        viewModelScope.launch {
            fetchCart.execute().also { result: Result<List<Dish>> ->
                val action = when (result) {
                    is Result.Success -> {
                        if (result.data.isEmpty()) {
                            Action.CartEmpty
                        } else {
                            Action.CartLoadingSuccess(result.data)
                        }
                    }
                    is Result.Error -> Action.CartLoadingFailure
                }
                sendAction(action)
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            clearCart.execute().also { result ->
                val action = when (result) {
                    is Result.Success -> {
                        Action.CartEmpty
                    }
                    is Result.Error -> {
                        Action.CartLoadingFailure
                    }
                }
                sendAction(action)
            }
        }
    }

    fun sendCart(userName: String, phone: String, dishes: List<Dish>) {
        val menu = dishes.map { it.toCartDish() }
        val cart = Cart(
            userName = userName,
            phone = phone,
            menu = menu
        )
        viewModelScope.launch {
            sendCart.execute(cart).also { result ->
                val action = when (result) {
                    is Result.Success -> {
                        Action.CartEmpty
                    }
                    else -> {
                        Action.CartLoadingSuccess(state.cart)
                    }
                }
                sendAction(action)
            }
        }
    }


    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.CartLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            isEmpty = false,
            cart = viewAction.cart
        )
        is Action.CartLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            isEmpty = false,
            cart = listOf()
        )
        else -> state.copy(
            isLoading = false,
            isError = false,
            isEmpty = true,
            cart = listOf()
        )
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val isEmpty: Boolean = false,
        val cart: List<Dish> = listOf()
    ) : BaseViewState

    internal sealed interface Action : BaseAction {
        class CartLoadingSuccess(val cart: List<Dish>) : Action
        object CartEmpty : Action
        object CartLoadingFailure : Action
    }
}