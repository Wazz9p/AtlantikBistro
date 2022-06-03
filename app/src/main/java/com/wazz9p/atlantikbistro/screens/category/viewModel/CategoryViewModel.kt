package com.wazz9p.atlantikbistro.screens.category.viewModel

import androidx.lifecycle.viewModelScope
import com.wazz9p.core.base.BaseAction
import com.wazz9p.core.base.BaseViewModel
import com.wazz9p.core.base.BaseViewState
import com.wazz9p.domain.model.menu.Category
import com.wazz9p.domain.usecase.menu.FetchCategory
import com.wazz9p.core.base.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryViewModel @Inject constructor(private val fetchCategory: FetchCategory) :
    BaseViewModel<CategoryViewModel.ViewState, CategoryViewModel.Action>(ViewState()) {

    override fun onLoadData() {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            fetchCategory.execute().also { result ->
                val action = when (result) {
                    is Result.Success -> {
                        if (result.data.isEmpty()) {
                            Action.CategoryListLoadingFailure
                        } else {
                            Action.CategoryListLoadingSuccess(result.data)
                        }
                    }
                    is Result.Error -> Action.CategoryListLoadingFailure
                }
                sendAction(action)
            }
        }
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.CategoryListLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            categoryList = listOf()
        )
        is Action.CategoryListLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            categoryList = viewAction.categoryList
        )
    }

    data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val categoryList: List<Category> = listOf()
    ) : BaseViewState

    sealed interface Action : BaseAction {
        class CategoryListLoadingSuccess(val categoryList: List<Category>) : Action
        object CategoryListLoadingFailure : Action
    }
}