package com.wazz9p.atlantikbistro.screens.menu.viewModel

import androidx.lifecycle.viewModelScope
import com.wazz9p.core.base.BaseAction
import com.wazz9p.core.base.BaseViewModel
import com.wazz9p.core.base.BaseViewState
import com.wazz9p.core.base.Result
import com.wazz9p.domain.model.menu.Dish
import com.wazz9p.domain.usecase.menu.FetchMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MenuViewModel @Inject constructor(
    private val fetchMenuUseCase: FetchMenu
) : BaseViewModel<MenuViewModel.ViewState, MenuViewModel.Action>(ViewState()) {

    override fun onLoadData() {
        getMenu()
    }

    private var categoryId: Int = 1

    private fun getMenu() {
        viewModelScope.launch {
            fetchMenuUseCase.execute(categoryId).also { result ->
                val action = when (result) {
                    is Result.Success -> {
                        if (result.data.isEmpty()) {
                            Action.MenuLoadingFailure
                        } else {
                            Action.MenuLoadingSuccess(result.data)
                        }
                    }
                    is Result.Error -> Action.MenuLoadingFailure
                }
                sendAction(action)
            }
        }
    }

    fun getCategory(id: Int) {
        categoryId = id
    }


    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.MenuLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            menu = listOf()
        )
        is Action.MenuLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            menu = viewAction.menu
        )
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val menu: List<Dish> = listOf()
    ) : BaseViewState

    internal sealed interface Action : BaseAction {
        class MenuLoadingSuccess(val menu: List<Dish>) : Action
        object MenuLoadingFailure : Action
    }
}
