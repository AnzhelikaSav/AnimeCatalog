package com.example.animecatalog.features.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.animecatalog.R
import com.example.animecatalog.app.DiProvider
import com.example.animecatalog.databinding.FragmentAnimeDetailsBinding
import com.example.animecatalog.navigation.Router
import com.example.animecatalog.toRating
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

        viewModel.anime.observe(viewLifecycleOwner) {
            setInfo(it)
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
}