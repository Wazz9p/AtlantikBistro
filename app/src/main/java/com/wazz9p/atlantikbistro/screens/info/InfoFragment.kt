package com.wazz9p.atlantikbistro.screens.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wazz9p.atlantikbistro.R
import com.wazz9p.atlantikbistro.databinding.FragmentInfoBinding
import com.wazz9p.core.delegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : Fragment(R.layout.fragment_info) {

    private val binding: FragmentInfoBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupReviewButton()
    }

    private fun setupReviewButton() {
        binding.reviewNavigateButton.setOnClickListener {
            findNavController().navigate(InfoFragmentDirections.actionInfoFragmentToReviewFragment())
        }
    }
}