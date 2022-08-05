package com.richard.vinyx.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.richard.vinyx.core.databinding.ItemFilmBinding
import com.richard.vinyx.core.domain.model.Game

class GameAdapter : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    private val listGame = ArrayList<Game>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(list: List<Game>?) {
        if (list == null) return
        listGame.clear()
        listGame.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private var binding: ItemFilmBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(game: Game) {
            var tempText = ""
            game.genres.forEachIndexed { index, genre ->
                if (index == 0) tempText += genre.name
                else if (index in 1..2) tempText += " \u2022 ${genre.name}"
            }
            with(binding) {
                Glide.with(itemView.context)
                    .load(game.backgroundImage)
                    .into(itemBgImage)
                tvHomeName.text = game.name
                tvHomeRating.text = game.rating.toString() + " (${game.ratingsCount})"
                tvHomeGenres.text = tempText
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = listGame[position]
        holder.bind(game)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(game) }
    }

    override fun getItemCount(): Int = listGame.size

    interface OnItemClickCallback{
        fun onItemClicked(data: Game)
    }
}