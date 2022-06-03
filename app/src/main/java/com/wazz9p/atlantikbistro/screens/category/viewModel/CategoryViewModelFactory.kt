package com.wazz9p.atlantikbistro.screens.category.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wazz9p.domain.usecase.menu.FetchCategory
import javax.inject.Inject

class CategoryViewModelFactory @Inject constructor(
    private val fetchCategory: FetchCategory
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == CategoryViewModel::class)
        return CategoryViewModel(fetchCategory) as T
    }
}