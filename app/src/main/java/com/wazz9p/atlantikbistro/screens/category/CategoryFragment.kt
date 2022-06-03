package com.wazz9p.atlantikbistro.screens.category

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.wazz9p.atlantikbistro.R
import com.wazz9p.atlantikbistro.screens.category.adapter.CategoryViewPagerAdapter
import com.wazz9p.atlantikbistro.appComponent
import com.wazz9p.atlantikbistro.databinding.FragmentCategoryBinding
import com.wazz9p.atlantikbistro.screens.category.viewModel.CategoryViewModel
import com.wazz9p.core.delegate.viewBinding
import com.wazz9p.core.extension.disableTooltip

class CategoryFragment : Fragment(R.layout.fragment_category) {

    private val binding: FragmentCategoryBinding by viewBinding()
    private val viewModel: CategoryViewModel by viewModels()


    private val stateObserver = Observer<CategoryViewModel.ViewState> {
        adapter?.categoryList = it.categoryList
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    private var adapter: CategoryViewPagerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        initTabs()
        viewModel.loadData()
    }

    private fun setupAdapter() {
        adapter = CategoryViewPagerAdapter(this)
        binding.categoryViewPager.adapter = adapter
    }

    private fun initTabs() {
        TabLayoutMediator(
            binding.categoryTabLayout,
           binding.categoryViewPager
        ) { tab, position ->

        }.attach()
       binding.categoryTabLayout.disableTooltip()
    }
}