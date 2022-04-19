package com.example.retrofit.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.data.models.Result
import com.example.retrofit.databinding.ItemViewBinding
import com.squareup.picasso.Picasso


class MoviesAdapter(
    private val listOfMovies: List<Result>,
    private val onItemClick: (Result) -> Unit
) : RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.apply {
            listOfMovies[position].apply {
                val pathOfImage = "https://image.tmdb.org/t/p/w500" + this.poster_path
                Picasso.get().load(pathOfImage).into(binding.movieImage)
                binding.titleTv.text = title
                binding.descriptionTv.text = overview
                binding.itemContainer.setOnClickListener { onItemClick(this) }
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfMovies.size
    }
}
class MoviesViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)