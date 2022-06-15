package com.wazz9p.atlantikbistro.screens.news.newsDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.wazz9p.atlantikbistro.R
import com.wazz9p.atlantikbistro.databinding.FragmentNewsDetailBinding
import com.wazz9p.core.delegate.observer
import com.wazz9p.core.delegate.viewBinding
import com.wazz9p.core.extension.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {

    private val binding: FragmentNewsDetailBinding by viewBinding()
    private val viewModel: NewsDetailViewModel by viewModels()

    private val stateObserver = Observer<NewsDetailViewModel.ViewState> {
        url = it.news?.imageUrl
        binding.newsDetailTitle.text = it.news?.title
        binding.newsDetailSubtitle.text = it.news?.subtitle
        binding.newsDetailText.text = it.news?.text
    }

    private var url by observer<String?>(null) {
        if (it == null || it.isEmpty()) {
            setDefaultImage()
        } else {
            setImage(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.stateLiveData, stateObserver)
        viewModel.loadData()
    }

    private fun setDefaultImage() {
        context?.let {
            Glide.with(it)
                .load("https://i.pinimg.com/originals/94/ee/2f/94ee2fda4931c26b3c55ed23d28e885e.png")
                .fitCenter()
                .into(binding.newsDetailImage)
        }
    }

    private fun setImage(url: String) {
        context?.let {
            Glide.with(it)
                .load(url)
                .fitCenter()
                .into(binding.newsDetailImage)
        }
    }
}