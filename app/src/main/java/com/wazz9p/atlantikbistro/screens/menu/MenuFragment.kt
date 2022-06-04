package com.wazz9p.atlantikbistro.screens.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.wazz9p.atlantikbistro.R
import com.wazz9p.atlantikbistro.screens.menu.adapter.MenuAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu) {

    companion object {
        const val ARG_OBJECT = "categoryId"
    }

    private var adapter: MenuAdapter? = null
    private var currentCategory: Int = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf {
            it.containsKey(ARG_OBJECT)
        }?.apply {
            currentCategory = getInt(ARG_OBJECT)
        }
    }
}