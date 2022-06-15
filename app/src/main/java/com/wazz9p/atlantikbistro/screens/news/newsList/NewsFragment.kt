package com.wazz9p.atlantikbistro.screens.news.newsList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.wazz9p.atlantikbistro.R
import com.wazz9p.atlantikbistro.databinding.FragmentNewsBinding
import com.wazz9p.atlantikbistro.screens.news.newsList.adapter.NewsAdapter
import com.wazz9p.atlantikbistro.screens.news.newsList.viewModel.NewsViewModel
import com.wazz9p.core.delegate.viewBinding
import com.wazz9p.core.extension.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {

    private val binding: FragmentNewsBinding by viewBinding()
    private val viewModel: NewsViewModel by viewModels()

    private val stateObserver = Observer<NewsViewModel.ViewState> {
        adapter.newsList = it.newsList
        binding.newsSwipeLayout.isRefreshing = it.isLoading
    }

    @Inject
    internal lateinit var adapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.stateLiveData, stateObserver)
        viewModel.loadData()

        setupAdapter()
        setupRefresh()
    }

    private fun setupAdapter() {
        adapter.setOnDebouncedClickListener {
            findNavController().navigate(
                NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(it)
            )
        }
        binding.newsRecyclerView.adapter = adapter
    }

    private fun setupRefresh() {
        binding.newsSwipeLayout.setOnRefreshListener {
            adapter.newsList = listOf()
            MainScope().launch {
                viewModel.loadData()
            }
        }
    }
}