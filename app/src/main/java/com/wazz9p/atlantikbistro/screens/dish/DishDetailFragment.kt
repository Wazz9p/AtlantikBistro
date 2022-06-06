package com.wazz9p.atlantikbistro.screens.dish

import androidx.fragment.app.Fragment
import com.wazz9p.atlantikbistro.R
import com.wazz9p.atlantikbistro.databinding.FragmentDishDetailBinding
import com.wazz9p.core.delegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DishDetailFragment : Fragment(R.layout.fragment_dish_detail) {

    private val binding: FragmentDishDetailBinding by viewBinding()

}