package com.wazz9p.atlantikbistro.screens.category.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wazz9p.atlantikbistro.screens.menu.MenuFragment
import com.wazz9p.core.delegate.observer
import com.wazz9p.domain.model.menu.Category

class CategoryViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    var categoryList: List<Category> by observer(listOf()) {
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = categoryList.size

    override fun createFragment(position: Int): Fragment = MenuFragment()

}