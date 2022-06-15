package com.wazz9p.atlantikbistro.screens.news.newsList.viewModel

import androidx.lifecycle.viewModelScope
import com.wazz9p.core.base.BaseAction
import com.wazz9p.core.base.BaseViewModel
import com.wazz9p.core.base.BaseViewState
import com.wazz9p.core.base.Result
import com.wazz9p.domain.model.news.News
import com.wazz9p.domain.usecase.news.FetchNewsList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class NewsViewModel @Inject constructor(
    private val fetchNewsList: FetchNewsList
) : BaseViewModel<NewsViewModel.ViewState, NewsViewModel.Action>(ViewState()) {

    override fun onLoadData() {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            fetchNewsList.execute().also { result: Result<List<News>> ->
                val action = when (result) {
                    is Result.Success -> {
                        if (result.data.isEmpty()) {
                            Action.NewsLoadingFailure
                        } else {
                            Action.NewsLoadingSuccess(result.data)
                        }
                    }
                    is Result.Error -> Action.NewsLoadingFailure
                }
                sendAction(action)
            }
        }
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.NewsLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            newsList = viewAction.newsList
        )
        is Action.NewsLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            newsList = listOf()
        )
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val newsList: List<News> = listOf()
    ) : BaseViewState

    internal sealed interface Action : BaseAction {
        class NewsLoadingSuccess(val newsList: List<News>) : Action
        object NewsLoadingFailure : Action
    }
}