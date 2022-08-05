package com.richard.vinyx.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.richard.vinyx.R
import com.richard.vinyx.core.data.Resource
import com.richard.vinyx.core.domain.model.Detail
import com.richard.vinyx.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val detailViewModel : DetailViewModel by viewModel()
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.detailToolbar)
            supportActionBar?.title = null
        }

        val game = DetailFragmentArgs.fromBundle(arguments as Bundle).game
        var isFavorite = game.isFavorite
        setFavoriteButton(game.isFavorite)
        binding.fabDetailFavorite.setOnClickListener {
            isFavorite = !isFavorite
            detailViewModel.setFavoriteGame(game, isFavorite)
            setFavoriteButton(isFavorite)
        }

        detailViewModel.getGameDetail(game.id).observe(viewLifecycleOwner) { detail ->
            when (detail) {
                is Resource.Success -> {
                    setData(detail.data)
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {

                }
            }
        }
    }

    private fun setData(detail: Detail?) {
        var tempText = ""
        detail?.let {
            it.genres.forEachIndexed { index, genre ->
                tempText += if (index == 0) genre.name else ", ${genre.name}"
            }
            binding.apply {
                (activity as AppCompatActivity).supportActionBar?.title = it.name
                Glide.with(requireContext())
                    .load(it.backgroundImage)
                    .into(ivDetailImage)
                tvDetailRating.text = it.rating.toString() + " (${it.ratingsCount})"
                tvDetailGenres.text = tempText
                tvDetailDesc.text = it.descriptionRaw
            }
        }

    }

    private fun setFavoriteButton(isFavorite: Boolean?) {
        if (isFavorite == true) {
            binding.fabDetailFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_red))
        } else {
            binding.fabDetailFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_white))
        }
    }
}