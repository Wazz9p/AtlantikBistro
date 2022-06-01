package com.wazz9p.atlantikbistro.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.wazz9p.atlantikbistro.R
import com.wazz9p.atlantikbistro.databinding.FragmentMainBinding
import com.wazz9p.core.delegate.viewBinding
import com.wazz9p.core.extension.disableTooltip

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController =
            (childFragmentManager.findFragmentById(R.id.mainContainerView) as NavHostFragment)
                .navController

        initBottomNavigation(navController)
    }

    private fun initBottomNavigation(navController: NavController) {
        val selectedNavController = MutableLiveData<NavController>()
        selectedNavController.value = navController
        binding.mainBottomNavigationView.setupWithNavController(navController)
        binding.mainBottomNavigationView.background = null
        binding.mainBottomNavigationView.setOnItemReselectedListener { }
        binding.mainBottomNavigationView.disableTooltip()
    }
}