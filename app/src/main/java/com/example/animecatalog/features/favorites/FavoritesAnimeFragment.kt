package com.example.animecatalog.features.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animecatalog.R
import com.example.animecatalog.app.DiProvider
import com.example.animecatalog.common.recycler.SpaceItemDecoration
import com.example.animecatalog.databinding.FragmentFavoriteAnimeBinding
import com.example.animecatalog.navigation.Router
import javax.inject.Inject

class FavoritesAnimeFragment: Fragment(R.layout.fragment_favorite_anime) {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var vmFactory: FavoritesAnimeViewModelFactory

    private val viewModel: FavoritesAnimeViewModel by viewModels { vmFactory }

    private lateinit var binding: FragmentFavoriteAnimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DiProvider.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoriteAnimeBinding.bind(view)

        setupToolbar()
        setupAdapter()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            router.back()
        }
    }

    private fun setupAdapter() {
        val adapter = FavoritesAdapter(viewModel::onDeleteClick)
        val decoration = SpaceItemDecoration(
            spaceSize = resources.getDimensionPixelSize(
                R.dimen.padding_12
            ),
            columns = 2
        )
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.addItemDecoration(decoration)
        binding.recyclerView.adapter = adapter

        viewModel.favorites.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

}