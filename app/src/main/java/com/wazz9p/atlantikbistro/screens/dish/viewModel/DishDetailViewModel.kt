package com.wazz9p.atlantikbistro.screens.dish.viewModel

import com.wazz9p.core.base.BaseAction
import com.wazz9p.core.base.BaseViewModel
import com.wazz9p.core.base.BaseViewState
import com.wazz9p.domain.model.menu.Dish
import com.wazz9p.domain.usecase.menu.FetchDish
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DishDetailViewModel @Inject constructor(
    private val fetchDish: FetchDish
) : BaseViewModel<DishDetailViewModel.ViewState, DishDetailViewModel.Action>(ViewState()) {

    private var dishId: Int = 0

    override fun onLoadData() {
        getDish(dishId)
    }

    private fun getDish(id: Int) {

    }

    fun getDishId(id: Int) {
        dishId = id
    }

    override fun onReduceState(viewAction: Action): ViewState {
        TODO("Not yet implemented")
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val dish: List<Dish> = listOf()
    ) : BaseViewState

    internal sealed interface Action : BaseAction {
        class DishLoadingSuccess(val dish: Dish) : Action
        object DishLoadingFailure : Action
    }
}