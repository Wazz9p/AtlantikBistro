package com.wazz9p.atlantikbistro.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.wazz9p.atlantikbistro.R
import com.wazz9p.atlantikbistro.databinding.FragmentCategoryBinding
import com.wazz9p.atlantikbistro.viewModel.menu.CategoryViewModel
import com.wazz9p.core.delegate.viewBinding

class CategoryFragment : Fragment(R.layout.fragment_category) {

    private val binding: FragmentCategoryBinding by viewBinding()
    private val viewModel: CategoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}