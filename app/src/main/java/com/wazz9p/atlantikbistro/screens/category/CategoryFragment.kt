package com.wazz9p.atlantikbistro.screens.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.wazz9p.atlantikbistro.R
import com.wazz9p.atlantikbistro.screens.category.adapter.CategoryViewPagerAdapter
import com.wazz9p.atlantikbistro.databinding.FragmentCategoryBinding
import com.wazz9p.atlantikbistro.screens.category.viewModel.CategoryViewModel
import com.wazz9p.core.delegate.viewBinding
import com.wazz9p.core.extension.disableTooltip
import com.wazz9p.core.extension.observe
import com.wazz9p.domain.model.menu.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category) {

    private val binding: FragmentCategoryBinding by viewBinding()
    private val viewModel: CategoryViewModel by viewModels()

    private var categoryList: List<Category> = listOf()

    private val stateObserver = Observer<CategoryViewModel.ViewState> {
        categoryList = it.categoryList
        adapter?.categoryList = it.categoryList
    }


    private var adapter: CategoryViewPagerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.stateLiveData, stateObserver)
        viewModel.loadData()

        setupSearch()
        setupAdapter()
        initTabs()
    }

    private fun setupAdapter() {
        adapter = CategoryViewPagerAdapter(categoryList, this)
        binding.categoryViewPager.adapter = adapter
    }

    private fun initTabs() {
        TabLayoutMediator(
            binding.categoryTabLayout,
            binding.categoryViewPager
        ) { tab, position ->
            tab.text = adapter?.categoryList?.get(position)?.name
        }.attach()
        binding.categoryTabLayout.disableTooltip()
    }

    private fun setupSearch() {
        binding.categorySearchContainer.setOnClickListener {
            findNavController().navigate(CategoryFragmentDirections.actionCategoryFragmentToSearchFragment())
        }
    }
}