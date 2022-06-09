package com.wazz9p.atlantikbistro.screens.news.newsList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wazz9p.atlantikbistro.databinding.ItemNewsListBinding
import com.wazz9p.core.delegate.observer
import com.wazz9p.core.extension.setOnDebouncedClickListener
import com.wazz9p.domain.model.news.News

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var newsList: List<News> by observer(listOf()) {
        notifyDataSetChanged()
    }

    private var onDebouncedClickListener: ((news: News) -> Unit)? = null

    fun setOnDebouncedClickListener(listener: (news: News) -> Unit) {
        this.onDebouncedClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsListBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size

    inner class ViewHolder(private val binding: ItemNewsListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var url by observer<String?>(null) {
            if (it == null || it.isEmpty()) {
                setDefaultImage()
            } else {
                setImage(it)
            }
        }

        fun bind(news: News) {
            itemView.setOnDebouncedClickListener { onDebouncedClickListener?.invoke(news) }
            url = news.imageUrl
            binding.newsItemTitle.text = news.title
        }

        private fun setDefaultImage() {
            Glide.with(itemView.context)
                .load("https://c.tenor.com/NkAegm0IP8IAAAAC/popcat.gif")
                .fitCenter()
                .into(binding.newsItemImage)
        }

        private fun setImage(url: String) {
            Glide.with(itemView.context)
                .load(url)
                .fitCenter()
                .into(binding.newsItemImage)
        }
    }
}