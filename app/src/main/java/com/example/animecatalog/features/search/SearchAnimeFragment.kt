package com.example.animecatalog.features.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animecatalog.R
import com.example.animecatalog.app.DiProvider
import com.example.animecatalog.common.recycler.SpaceItemDecoration
import com.example.animecatalog.databinding.FragmentSearchAnimeBinding
import com.example.animecatalog.common.adapter.AnimeAdapter
import com.example.animecatalog.navigation.Router
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchAnimeFragment: Fragment(R.layout.fragment_search_anime) {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var vmFactory: SearchAnimeViewModelFactory

    private val viewModel: SearchAnimeViewModel by viewModels { vmFactory }

    private lateinit var binding: FragmentSearchAnimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DiProvider.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchAnimeBinding.bind(view)

        binding.etSearch.addTextChangedListener {
            viewModel.onQueryChange(it.toString())
        }

        setupAdapter()
    }

    private fun setupAdapter() {
        val adapter = AnimeAdapter(::navigateToDetails)
        val decoration = SpaceItemDecoration(
            spaceSize = resources.getDimensionPixelSize(
                R.dimen.padding_12
            )
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(decoration)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.animeList.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun navigateToDetails(id: Int) {
        val direction =
            SearchAnimeFragmentDirections.actionSearchAnimeFragmentToAnimeDetailsFragment(id)
        router.navigateTo(direction)
    }
}