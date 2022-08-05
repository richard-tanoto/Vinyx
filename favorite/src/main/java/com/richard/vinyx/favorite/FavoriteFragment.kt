package com.richard.vinyx.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.richard.vinyx.core.domain.model.Game
import com.richard.vinyx.core.ui.GameAdapter
import com.richard.vinyx.favorite.databinding.FragmentFavoriteBinding
import com.richard.vinyx.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.favoriteToolbar)
            supportActionBar?.title = null
        }

        val gameAdapter = GameAdapter()

        binding.rvFavoriteFilms.apply {
            layoutManager = GridLayoutManager(view.context, 2)
            setHasFixedSize(true)
            adapter = gameAdapter
        }

        gameAdapter.setOnItemClickCallback(object : GameAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Game) {
                val toDetailFragment = FavoriteFragmentDirections.actionFavoriteFragmentToFavoriteDetailFragment(data)
                view.findNavController().navigate(toDetailFragment)
            }
        })

        favoriteViewModel.favoriteGames.observe(viewLifecycleOwner) { games ->
            gameAdapter.setData(games)
            binding.tvFavoriteEmpty.visibility = if (games.isEmpty()) View.VISIBLE else View.GONE
        }
    }
}