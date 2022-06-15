package com.wazz9p.atlantikbistro.screens.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wazz9p.atlantikbistro.databinding.ItemCartListBinding
import com.wazz9p.core.delegate.observer
import com.wazz9p.core.extension.setOnDebouncedClickListener
import com.wazz9p.domain.model.menu.Dish
import javax.inject.Inject

internal class CartAdapter @Inject constructor() : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    var cart: List<Dish> by observer(listOf()) {
        notifyDataSetChanged()
    }

    private var onDebouncedClickListener: ((dish: Dish) -> Unit)? = null

    fun setOnDebouncedClickListener(listener: (dish: Dish) -> Unit) {
        this.onDebouncedClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCartListBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cart[position])
    }

    override fun getItemCount(): Int = cart.size

    internal inner class ViewHolder(private val binding: ItemCartListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var url by observer<String?>(null) {
            if (it == null || it.isEmpty()) {
                setDefaultImage()
            } else {
                setImage(it)
            }
        }

        fun bind(dish: Dish) {
            url = dish.image
            binding.cartDishName.text = dish.name
            binding.cartDishPrice.text = dish.priceTag
            binding.cartDishWeight.text = dish.weight
        }

        private fun setDefaultImage() {
            Glide.with(itemView.context)
                .load("https://i.pinimg.com/originals/94/ee/2f/94ee2fda4931c26b3c55ed23d28e885e.png")
                .fitCenter()
                .into(binding.cartImageView)
        }

        private fun setImage(url: String) {
            Glide.with(itemView.context)
                .load(url)
                .fitCenter()
                .into(binding.cartImageView)
        }
    }
}