package com.example.animecatalog.features.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animecatalog.databinding.ItemFavoriteAnimeBinding
import com.example.domain.models.Anime

class FavoritesAdapter(
    private val onDeleteClick: (Int) -> Unit
) : ListAdapter<Anime, FavoritesAdapter.Holder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            binding = ItemFavoriteAnimeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class Holder(private val binding: ItemFavoriteAnimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: Anime) {
            binding.apply {
                Glide.with(root)
                    .load(anime.imageUrl)
                    .centerCrop()
                    .into(ivImage)
                tvTitle.text = anime.title
                tvDelete.setOnClickListener {
                    onDeleteClick(anime.id)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Anime>() {
        override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
            return oldItem.title == newItem.title
                    && oldItem.imageUrl == newItem.imageUrl
        }
    }

}