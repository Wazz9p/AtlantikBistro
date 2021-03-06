package com.wazz9p.atlantikbistro.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.wazz9p.atlantikbistro.R
import com.wazz9p.atlantikbistro.databinding.FragmentMainBinding
import com.wazz9p.atlantikbistro.screens.main.viewModel.MainFragmentViewModel
import com.wazz9p.core.delegate.viewBinding
import com.wazz9p.core.extension.disableTooltip
import com.wazz9p.core.extension.observe
import com.wazz9p.core.extension.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {


    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel: MainFragmentViewModel by viewModels()

    private val stateObserver = Observer<MainFragmentViewModel.ViewState> {
        binding.bottomAppBar.visible = it.isNavigationScreen
        binding.fabAddToCart.visible = it.isNavigationScreen
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController =
            (childFragmentManager.findFragmentById(R.id.mainContainerView) as NavHostFragment)
                .navController

        initBottomNavigation(navController)
        observe(viewModel.stateLiveData, stateObserver)
    }

    private fun initBottomNavigation(navController: NavController) {
        val selectedNavController = MutableLiveData<NavController>()
        selectedNavController.value = navController
        selectedNavController.observe(viewLifecycleOwner) {
            viewModel.navigationControllerChanged(it)
        }
        binding.mainBottomNavigationView.setupWithNavController(navController)
        binding.mainBottomNavigationView.background = null
        binding.mainBottomNavigationView.setOnItemReselectedListener { }
        binding.mainBottomNavigationView.disableTooltip()
        binding.fabAddToCart.setOnClickListener {
            navigateToCart(navController)
        }
    }

    private fun navigateToCart(navController: NavController) {
        MainScope().launch {
            binding.fabAddToCart.isClickable = false
            delay(180)
            binding.fabAddToCart.isClickable = true
            navController.navigate(R.id.nav_graph_cart)
        }
    }
}