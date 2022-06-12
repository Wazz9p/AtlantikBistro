package com.wazz9p.atlantikbistro.screens.news.newsDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.wazz9p.core.base.BaseAction
import com.wazz9p.core.base.BaseViewModel
import com.wazz9p.core.base.BaseViewState
import com.wazz9p.domain.model.news.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class NewsDetailViewModel @Inject constructor(
    private val savedState: SavedStateHandle
) :
    BaseViewModel<NewsDetailViewModel.ViewState, NewsDetailViewModel.Action>(ViewState()) {

    override fun onLoadData() {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            savedState.get<News>("news").also {
                val action = when (it) {
                    is News -> Action.NewsLoadingSuccess(it)
                    else -> Action.NewsLoadingFailure
                }
                sendAction(action)
            }
        }
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.NewsLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            news = viewAction.news
        )
        is Action.NewsLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            news = null
        )
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val news: News? = null
    ) : BaseViewState

    internal sealed interface Action : BaseAction {
        class NewsLoadingSuccess(val news: News) : Action
        object NewsLoadingFailure : Action
    }
}