package com.wazz9p.atlantikbistro.screens.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.wazz9p.atlantikbistro.R
import com.wazz9p.atlantikbistro.databinding.FragmentMenuBinding
import com.wazz9p.atlantikbistro.screens.category.CategoryFragmentDirections
import com.wazz9p.atlantikbistro.screens.menu.adapter.MenuAdapter
import com.wazz9p.atlantikbistro.screens.menu.viewModel.MenuViewModel
import com.wazz9p.core.delegate.viewBinding
import com.wazz9p.core.extension.observe
import com.wazz9p.core.extension.visible
import com.wazz9p.domain.model.menu.Dish
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu) {

    companion object {
        const val ARG_OBJECT = "categoryId"
    }

    private val binding: FragmentMenuBinding by viewBinding()
    private val viewModel: MenuViewModel by viewModels()

    @Inject
    internal lateinit var adapter: MenuAdapter

    private val stateObserver = Observer<MenuViewModel.ViewState> {
        adapter.menu = it.menu
        binding.menuSwipeLayout.isRefreshing = it.isLoading
        binding.errorImageContainer.visible = it.isError
    }

    private var currentCategory: Int = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf {
            it.containsKey(ARG_OBJECT)
        }?.apply {
            currentCategory = getInt(ARG_OBJECT)
        }

        observe(viewModel.stateLiveData, stateObserver)
        viewModel.getCategory(currentCategory)
        viewModel.loadData()

        setupAdapter()
        setupRefreshAdapter()
    }


    private fun setupAdapter() {
        adapter.setOnDebouncedClickListener {
            findNavController().navigate(
                CategoryFragmentDirections.actionCategoryFragmentToDishDetailFragment(it)
            )
        }
        binding.menuRecyclerView.adapter = adapter
    }

    private fun setupRefreshAdapter() {
        binding.menuSwipeLayout.setOnRefreshListener {
            adapter.menu = listOf()
            MainScope().launch {
                viewModel.loadData()
            }
        }
    }
}