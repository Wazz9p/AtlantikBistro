package com.wazz9p.atlantikbistro.screens.dish.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.wazz9p.core.base.BaseAction
import com.wazz9p.core.base.BaseViewModel
import com.wazz9p.core.base.BaseViewState
import com.wazz9p.domain.model.menu.Dish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DishDetailViewModel @Inject constructor(
    private val savedState: SavedStateHandle
) : BaseViewModel<DishDetailViewModel.ViewState, DishDetailViewModel.Action>(ViewState()) {

    override fun onLoadData() {
        getDish()
    }

    private fun getDish() {
        viewModelScope.launch {
            savedState.get<Dish>("dish").also {
                val action = when (it) {
                    is Dish -> Action.DishLoadingSuccess(it)
                    else -> Action.DishLoadingFailure
                }
                sendAction(action)
            }
        }
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
            dish = null
        )
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val dish: Dish? = null
    ) : BaseViewState

    internal sealed interface Action : BaseAction {
        class DishLoadingSuccess(val dish: Dish) : Action
        object DishLoadingFailure : Action
    }
}