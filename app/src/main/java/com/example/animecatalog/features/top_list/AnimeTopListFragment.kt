package com.example.animecatalog.features.top_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animecatalog.R
import com.example.animecatalog.app.DiProvider
import com.example.animecatalog.common.recycler.SpaceItemDecoration
import com.example.animecatalog.databinding.FragmentAnimeTopListBinding
import com.example.animecatalog.navigation.Router
import javax.inject.Inject

class AnimeTopListFragment: Fragment(R.layout.fragment_anime_top_list) {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var vmFactory: AnimeTopListViewModelFactory

    private val viewModel: AnimeTopListViewModel by viewModels { vmFactory }

    private lateinit var binding: FragmentAnimeTopListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DiProvider.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAnimeTopListBinding.bind(view)

        viewModel.getTopAnime()
        setupAdapter()
    }

    private fun setupAdapter() {
        val adapter = AnimeAdapter()
        val decoration = SpaceItemDecoration(
            spaceSize = resources.getDimensionPixelSize(
                R.dimen.padding_12
            )
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(decoration)
        binding.recyclerView.adapter = adapter
        viewModel.animeList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}