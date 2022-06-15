package com.wazz9p.atlantikbistro.screens.dish

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.wazz9p.atlantikbistro.R
import com.wazz9p.atlantikbistro.databinding.FragmentDishDetailBinding
import com.wazz9p.atlantikbistro.screens.dish.viewModel.DishDetailViewModel
import com.wazz9p.core.delegate.observer
import com.wazz9p.core.extension.observe
import com.wazz9p.core.delegate.viewBinding
import com.wazz9p.core.extension.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DishDetailFragment : Fragment(R.layout.fragment_dish_detail) {

    private val binding: FragmentDishDetailBinding by viewBinding()
    private val viewModel: DishDetailViewModel by viewModels()

    private val stateObserver = Observer<DishDetailViewModel.ViewState> {
        url = it.dish?.image
        weight = it.dish?.weight
        binding.dishDetailPrice.text = it.dish?.detailPriceTag
        binding.dishDetailTitle.text = it.dish?.name
        binding.dishDetailDescription.text = it.dish?.description
    }

    private var url by observer<String?>(null) {
        if (it == null || it.isEmpty()) {
            setDefaultImage()
        } else {
            setImage(it)
        }
    }

    private var weight by observer<String?>(null) {
        if (it == null) {
            binding.dishDetailWeight.visible = false
        } else {
            binding.dishDetailWeight.text = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAddButton()
        observe(viewModel.stateLiveData, stateObserver)
        viewModel.loadData()
    }

    private fun setupAddButton() {
        binding.containerAddButton.setOnClickListener {
            viewModel.stateLiveData.value?.dish?.let { dish -> viewModel.addDish(dish) }
            findNavController().popBackStack()
        }
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