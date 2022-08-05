package com.richard.vinyx.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.richard.vinyx.R
import com.richard.vinyx.core.data.Resource
import com.richard.vinyx.core.domain.model.Game
import com.richard.vinyx.core.ui.GameAdapter
import com.richard.vinyx.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.homeToolbar)
            supportActionBar?.title = null
            addMenuProvider(object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.app_menu, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return if (menuItem.itemId == R.id.favoriteNav) {
                        val toFavorite = HomeFragmentDirections.actionHomeFragmentToFavoriteNav()
                        requireView().findNavController().navigate(toFavorite)
                        true
                    } else false
                }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        }

        val gameAdapter = GameAdapter()

        binding.rvFilms.apply {
            layoutManager = GridLayoutManager(view.context, 2)
            setHasFixedSize(true)
            adapter = gameAdapter
        }

        gameAdapter.setOnItemClickCallback(object : GameAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Game) {
                val toDetailFragment = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
                view.findNavController().navigate(toDetailFragment)
            }
        })

        homeViewModel.games.observe(viewLifecycleOwner) { games ->
            when (games) {
                is Resource.Success -> {
                    binding.pbHomeLoading.visibility = View.GONE
                    binding.tvHomeError.visibility = View.GONE
                    gameAdapter.setData(games.data)
                }
                is Resource.Loading -> {
                    binding.pbHomeLoading.visibility = View.VISIBLE
                    binding.tvHomeError.visibility = View.GONE
                }
                is Resource.Error -> {
                    binding.pbHomeLoading.visibility = View.GONE
                    binding.tvHomeError.visibility = View.VISIBLE
                }
            }
        }
    }
}