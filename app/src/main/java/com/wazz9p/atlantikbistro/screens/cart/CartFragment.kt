package com.wazz9p.atlantikbistro.screens.cart

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.wazz9p.atlantikbistro.R
import com.wazz9p.atlantikbistro.databinding.FragmentCartBinding
import com.wazz9p.atlantikbistro.screens.cart.adapter.CartAdapter
import com.wazz9p.atlantikbistro.screens.cart.adapter.CartSwipeGesture
import com.wazz9p.atlantikbistro.screens.cart.viewModel.CartViewModel
import com.wazz9p.core.delegate.viewBinding
import com.wazz9p.core.extension.observe
import com.wazz9p.core.extension.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {

    private val binding: FragmentCartBinding by viewBinding()
    private val viewModel: CartViewModel by viewModels()

    private val stateObserver = Observer<CartViewModel.ViewState> {
        adapter.cart = it.cart
        binding.errorText.visible = it.isEmpty
    }

    @Inject
    internal lateinit var adapter: CartAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.stateLiveData, stateObserver)
        viewModel.loadData()

        context?.let { setupAlert(it) }

        setupExitButton()
        setupClearButton()
        setupAdapter()

    }

    private fun setupExitButton() {
        binding.cartExitButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupClearButton() {
        binding.cartClearButton.setOnClickListener {
            viewModel.clearCart()
        }
    }

    private fun setupAlert(context: Context) {

        val view = activity?.layoutInflater?.inflate(R.layout.dialog_review, null)

        val addContactDialog = AlertDialog.Builder(context)
            .setView(view)
            .setNegativeButton("Отмена") { _, _ -> }
            .setPositiveButton("Отправить заказ") { _, _ ->
                viewModel.sendCart(
                    userName = view?.findViewById<EditText>(R.id.name)?.text.toString(),
                    phone = view?.findViewById<EditText>(R.id.phoneNumber)?.text.toString(),
                    dishes = viewModel.stateLiveData.value?.cart ?: listOf()
                )
            }
            .create()

        binding.containerSendCartButton.setOnClickListener {
            addContactDialog.show()
        }
    }

    private fun setupAdapter() {
        binding.cartRecyclerView.adapter = adapter
        val swipeGesture = object : CartSwipeGesture(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteDish(adapter.getDish(viewHolder.adapterPosition))
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeGesture)
        itemTouchHelper.attachToRecyclerView(binding.cartRecyclerView)
    }
}