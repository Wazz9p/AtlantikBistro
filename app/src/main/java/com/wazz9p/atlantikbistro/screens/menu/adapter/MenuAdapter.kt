package com.wazz9p.atlantikbistro.screens.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wazz9p.atlantikbistro.databinding.ItemMenuListBinding
import com.wazz9p.core.delegate.observer
import com.wazz9p.core.extension.setOnDebouncedClickListener
import com.wazz9p.domain.model.menu.Dish
import javax.inject.Inject


internal class MenuAdapter @Inject constructor() : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    var menu: List<Dish> by observer(listOf()) {
        notifyDataSetChanged()
    }

    private var onDebouncedClickListener: ((dish: Dish) -> Unit)? = null

    fun setOnDebouncedClickListener(listener: (dish: Dish) -> Unit) {
        this.onDebouncedClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMenuListBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuAdapter.ViewHolder, position: Int) {
        holder.bind(menu[position])
    }

    override fun getItemCount(): Int = menu.size


    inner class ViewHolder(private val binding: ItemMenuListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var url by observer<String?>(null) {
            if (it == null || it.isEmpty()) {
                setDefaultImage()
            } else {
                setImage(it)
            }
        }


        fun bind(dish: Dish) {
            val dishPrice = dish.price + " р"
            itemView.setOnDebouncedClickListener { onDebouncedClickListener?.invoke(dish) }
            binding.dishNameItemTextView.text = dish.name
            url = dish.image
            binding.dishPriceItemTextView.text = dishPrice
            binding.dishDescriptionItemTextView.text = dish.weight
        }

        private fun setDefaultImage() {
            Glide.with(itemView.context)
                .load("https://i.pinimg.com/originals/94/ee/2f/94ee2fda4931c26b3c55ed23d28e885e.png")
                .fitCenter()
                .into(binding.dishItemImageView)
        }

        private fun setImage(url: String) {
            Glide.with(itemView.context)
                .load(url)
                .fitCenter()
                .into(binding.dishItemImageView)
        }
    }
}