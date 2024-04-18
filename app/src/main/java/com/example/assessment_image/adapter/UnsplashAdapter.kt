package com.example.assessment_image.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assessment_image.model.UnsplashResponse

class UnsplashAdapter : PagingDataAdapter<UnsplashResponse, RecyclerView.ViewHolder>(COMPARATOR) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val postsItem = getItem(position)
        if (postsItem != null) {
            (holder as? UnsplashViewHolder)?.bind(postsItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UnsplashViewHolder.create(parent)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<UnsplashResponse>() {
            override fun areItemsTheSame(oldItem: UnsplashResponse, newItem: UnsplashResponse): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UnsplashResponse, newItem: UnsplashResponse): Boolean =
                oldItem == newItem

        }
    }
}