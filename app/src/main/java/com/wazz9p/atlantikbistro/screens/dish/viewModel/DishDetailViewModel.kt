package com.wazz9p.atlantikbistro.screens.dish.viewModel

import androidx.lifecycle.viewModelScope
import com.wazz9p.core.base.BaseAction
import com.wazz9p.core.base.BaseViewModel
import com.wazz9p.core.base.BaseViewState
import com.wazz9p.core.base.Result
import com.wazz9p.domain.model.menu.Dish
import com.wazz9p.domain.usecase.menu.FetchDish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DishDetailViewModel @Inject constructor(
    private val fetchDish: FetchDish
) : BaseViewModel<DishDetailViewModel.ViewState, DishDetailViewModel.Action>(ViewState()) {

    private var dishId: Int = 1

    override fun onLoadData() {
        getDish(dishId)
    }

    private fun getDish(id: Int) {
        viewModelScope.launch {
            fetchDish.execute(id).also { result: Result<Dish> ->
                val action = when (result) {
                    is Result.Success -> Action.DishLoadingSuccess(listOf(result.data))
                    is Result.Error -> Action.DishLoadingFailure
                }
                sendAction(action)
            }
        }
    }

    fun getDishId(id: Int) {
        dishId = id
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.DishLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            dish = viewAction.dish
        )
        is Action.DishLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            dish = listOf()
        )
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val dish: List<Dish> = listOf()
    ) : BaseViewState

    internal sealed interface Action : BaseAction {
        class DishLoadingSuccess(val dish: List<Dish>) : Action
        object DishLoadingFailure : Action
    }
}