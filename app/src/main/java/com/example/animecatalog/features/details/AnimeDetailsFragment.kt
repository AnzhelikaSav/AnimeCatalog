package com.example.animecatalog.features.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.animecatalog.R
import com.example.animecatalog.app.DiProvider
import com.example.animecatalog.databinding.FragmentAnimeDetailsBinding
import com.example.animecatalog.navigation.Router
import com.example.animecatalog.toRating
import com.example.domain.RequestResult
import com.example.domain.models.Anime
import javax.inject.Inject

class AnimeDetailsFragment: Fragment(R.layout.fragment_anime_details) {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var vmFactoryAssisted: AnimeDetailsViewModelFactoryAssisted

    private val args: AnimeDetailsFragmentArgs by navArgs()

    private val viewModel: AnimeDetailsViewModel by viewModels {
        vmFactoryAssisted.create(args.id)
    }

    private lateinit var binding: FragmentAnimeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DiProvider.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAnimeDetailsBinding.bind(view)

        setObservers()
        setListeners()
    }

    private fun setObservers() {
        viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            val resId = if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            binding.fabFavorite.setImageResource(resId)
        }

        viewModel.result.observe(viewLifecycleOwner) { result ->
            binding.ivImage.isVisible = result is RequestResult.Success
            binding.scrollView.isVisible = result is RequestResult.Success
            binding.progressBar.isVisible = result == RequestResult.Loading
            binding.llError.isVisible = result is RequestResult.Error

            if (result is RequestResult.Success) {
                setInfo(result.data)
            }
        }
    }

    private fun setListeners() {
        binding.fabFavorite.setOnClickListener {
            addToFavoritesClick()
        }
        binding.btnRetry.setOnClickListener {
            viewModel.onRetryClick()
        }
    }

    private fun setInfo(anime: Anime) {
        with(binding) {
            Glide.with(root)
                .load(anime.largeImageUrl)
                .centerCrop()
                .into(ivImage)
            tvTitle.text = anime.title
            tvScore.text = anime.score.toString()
            rbScore.rating = anime.score.toRating()
            tvSynopsis.text = anime.synopsis
        }
    }

    private fun addToFavoritesClick() {
        viewModel.onAddToFavoritesClick()
    }
}