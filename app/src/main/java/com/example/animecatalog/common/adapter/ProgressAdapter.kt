package com.example.animecatalog.common.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.animecatalog.databinding.ItemProgressBinding

class ProgressAdapter(
  private val retry: () -> Unit
): LoadStateAdapter<ProgressAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        return Holder(
            binding = ItemProgressBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class Holder(
        private val binding: ItemProgressBinding
    ): RecyclerView.ViewHolder(binding.root)  {
        fun bind(loadState: LoadState) {
            Log.d("AAAA", loadState.toString())
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
                tvText.isVisible = loadState is LoadState.Error
                ivRetry.isVisible = loadState is LoadState.Error
                if (loadState is LoadState.Error) {
                    tvText.text = loadState.error.message
                    ivRetry.setOnClickListener { retry() }
                }
            }
        }
    }
}