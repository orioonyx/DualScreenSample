package com.kyungeun.dualscreensample.ui.posts

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kyungeun.dualscreensample.databinding.ItemPostBinding
import com.kyungeun.dualscreensample.model.Post

class PostAdapter(private val onItemClicked: (Post) -> Unit) :
    ListAdapter<Post, PostAdapter.PostViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding: ItemPostBinding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClicked(getItem(position))
        }
        holder.bind(getItem(position))
    }

    class PostViewHolder(private val itemBinding: ItemPostBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Post) {
            itemBinding.title.text = item.title
            itemBinding.subTitle.text = item.subTitle

            Glide.with(itemBinding.root)
                .load(item.image)
                .override(256, 256)
                .dontAnimate()
                .into(itemBinding.image)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Post>() {
            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
