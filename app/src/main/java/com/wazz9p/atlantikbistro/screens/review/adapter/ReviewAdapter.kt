package com.wazz9p.atlantikbistro.screens.review.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wazz9p.atlantikbistro.databinding.ItemReviewListBinding
import com.wazz9p.core.delegate.observer
import com.wazz9p.core.extension.visible
import com.wazz9p.domain.model.review.Review
import javax.inject.Inject


internal class ReviewAdapter @Inject constructor() :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    var reviewList: List<Review> by observer(listOf()) {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemReviewListBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewAdapter.ViewHolder, position: Int) {
        holder.bind(reviewList[position])
    }

    override fun getItemCount(): Int = reviewList.size

    internal inner class ViewHolder(private val binding: ItemReviewListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(review: Review) {

            if (review.adminMessage == null) {
                binding.adminReviewContainer.visible = false
            } else {
                binding.adminReviewContainer.visible = true
                binding.adminName.text = review.adminName
                binding.adminReviewText.text = review.adminMessage
            }
            binding.usernameTextview.text = review.name
            binding.userReviewText.text = review.message
        }
    }
}
