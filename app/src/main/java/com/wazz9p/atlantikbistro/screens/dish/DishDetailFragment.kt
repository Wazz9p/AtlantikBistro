package com.wazz9p.atlantikbistro.screens.dish

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.wazz9p.atlantikbistro.R
import com.wazz9p.atlantikbistro.databinding.FragmentDishDetailBinding
import com.wazz9p.atlantikbistro.screens.dish.viewModel.DishDetailViewModel
import com.wazz9p.atlantikbistro.screens.menu.viewModel.MenuViewModel
import com.wazz9p.core.delegate.observer
import com.wazz9p.core.extension.observe
import com.wazz9p.core.delegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DishDetailFragment : Fragment(R.layout.fragment_dish_detail) {

    companion object {
        const val ARG_OBJECT = "dishId"
    }

    private val binding: FragmentDishDetailBinding by viewBinding()
    private val viewModel: DishDetailViewModel by viewModels()

    private val stateObserver = Observer<DishDetailViewModel.ViewState> {
        url = it.dish[0].image
        binding.dishDetailPrice.text = it.dish[0].price
        binding.dishDetailTitle.text = it.dish[0].name
        val price = "В корзину за " + it.dish[0].price + "р"
        binding.dishDetailPrice.text = price
        binding.dishDetailDescription.text = it.dish[0].description
    }

    private var url by observer<String?>(null) {
        if (it == null || it.isEmpty()) {
            setDefaultImage()
        } else {
            setImage(it)
        }
    }

    private var currentDish: Int = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf {
            it.containsKey(ARG_OBJECT)
        }?.apply {
            currentDish = getInt(ARG_OBJECT)
        }

        viewModel.getDishId(currentDish)

        observe(viewModel.stateLiveData, stateObserver)
        viewModel.loadData()
    }

    private fun setDefaultImage() {
        context?.let {
            Glide.with(it)
                .load("https://i.pinimg.com/originals/94/ee/2f/94ee2fda4931c26b3c55ed23d28e885e.png")
                .fitCenter()
                .into(binding.dishDetailImage)
        }
    }

    private fun setImage(url: String) {
        context?.let {
            Glide.with(it)
                .load(url)
                .fitCenter()
                .into(binding.dishDetailImage)
        }
    }
}