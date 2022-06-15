package com.wazz9p.atlantikbistro.screens.review.viewModel

import androidx.lifecycle.viewModelScope
import com.wazz9p.core.base.BaseAction
import com.wazz9p.core.base.BaseViewModel
import com.wazz9p.core.base.BaseViewState
import com.wazz9p.core.base.Result
import com.wazz9p.domain.model.review.Review
import com.wazz9p.domain.usecase.review.FetchReview
import com.wazz9p.domain.usecase.review.InsertReview
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ReviewViewModel @Inject constructor(
    private val fetchReviews: FetchReview,
    private val sendReview: InsertReview
) : BaseViewModel<ReviewViewModel.ViewState, ReviewViewModel.Action>(ViewState()) {

    override fun onLoadData() {
        super.onLoadData()
        getReviews()
    }

    private fun getReviews() {
        viewModelScope.launch {
            fetchReviews.execute().also { result ->
                val action = when (result) {
                    is Result.Success -> {
                        if (result.data.isEmpty()) {
                            Action.ReviewsLoadingFailure
                        } else {
                            Action.ReviewsLoadingSuccess(result.data)
                        }
                    }
                    is Result.Error -> Action.ReviewsLoadingFailure
                }
                sendAction(action)
            }
        }
    }

    fun senReview(review: Review) {
        viewModelScope.launch {
            sendReview.execute(review)
        }
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.ReviewsLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            reviews = listOf()
        )
        is Action.ReviewsLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            reviews = viewAction.reviews
        )
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val reviews: List<Review> = listOf()
    ) : BaseViewState

    internal sealed interface Action : BaseAction {
        class ReviewsLoadingSuccess(val reviews: List<Review>) : Action
        object ReviewsLoadingFailure : Action
    }
}