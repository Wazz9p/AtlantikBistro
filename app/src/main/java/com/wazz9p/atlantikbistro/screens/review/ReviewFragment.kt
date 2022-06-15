package com.wazz9p.atlantikbistro.screens.review

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.wazz9p.atlantikbistro.R
import com.wazz9p.atlantikbistro.databinding.FragmentReviewBinding
import com.wazz9p.atlantikbistro.screens.review.adapter.ReviewAdapter
import com.wazz9p.atlantikbistro.screens.review.viewModel.ReviewViewModel
import com.wazz9p.core.delegate.viewBinding
import com.wazz9p.core.extension.observe
import com.wazz9p.domain.model.review.Review
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ReviewFragment : Fragment(R.layout.fragment_review) {

    private val binding: FragmentReviewBinding by viewBinding()
    private val viewModel: ReviewViewModel by viewModels()

    @Inject
    internal lateinit var adapter: ReviewAdapter

    private val stateObserver = Observer<ReviewViewModel.ViewState> {
        adapter.reviewList = it.reviews
        binding.reviewSwipeLayout.isRefreshing = it.isLoading
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let { setupAlert(it) }
        setupAdapter()
        setupRefreshAdapter()

        observe(viewModel.stateLiveData, stateObserver)
        viewModel.loadData()

    }

    private fun setupAlert(context: Context) {

        val view = activity?.layoutInflater?.inflate(R.layout.dialog_review, null)

        val addContactDialog = AlertDialog.Builder(context)
            .setView(view)
            .setNegativeButton("Отмена") { _, _ -> }
            .setPositiveButton("Отправить отзыв") { _, _ ->
                viewModel.senReview(
                    Review(
                        id = 0,
                        name = view?.findViewById<EditText>(R.id.name)?.text.toString(),
                        phoneNumber = view?.findViewById<EditText>(R.id.phoneNumber)?.text.toString(),
                        message = binding.reviewEdiText.text.toString(),
                        adminMessage = null
                    )
                )
            }
            .create()


        binding.sendButton.setOnClickListener {
            addContactDialog.show()
        }
    }

    private fun setupAdapter() {
        binding.reviewRecyclerView.adapter = adapter
    }

    private fun setupRefreshAdapter() {
        binding.reviewSwipeLayout.setOnRefreshListener {
            adapter.reviewList = listOf()
            MainScope().launch {
                viewModel.loadData()
            }
        }
    }
}