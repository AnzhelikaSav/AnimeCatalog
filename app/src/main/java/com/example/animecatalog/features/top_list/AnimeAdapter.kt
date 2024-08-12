package com.example.animecatalog.features.top_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.animecatalog.databinding.ItemAnimeCardBinding
import com.example.domain.models.Anime

class AnimeAdapter: ListAdapter<Anime, AnimeAdapter.Holder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            binding = ItemAnimeCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class Holder(private val binding: ItemAnimeCardBinding) : ViewHolder(binding.root) {
        fun bind(anime: Anime) {
            binding.apply {
                Glide.with(root)
                    .load(anime.imageUrl)
                    .into(ivImage)
                tvTitle.text = anime.title
                tvYear.text = if (anime.year > 0) anime.year.toString() else ""
                tvGenres.text = anime.genres.joinToString(", ")
                rbScore.rating = (anime.score / 2).toFloat()
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