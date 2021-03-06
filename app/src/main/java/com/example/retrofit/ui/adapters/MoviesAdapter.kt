package com.example.retrofit.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.data.models.Result
import com.example.retrofit.databinding.ItemViewBinding
import com.example.retrofit.utils.Constants
import com.squareup.picasso.Picasso


class MoviesAdapter(
   private val dataList: List<Result>,
    private val onItemClick: (Result) -> Unit
) : RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.apply {
            dataList[position].apply {
                val pathOfImage = Constants.IMAGE_URL + this.poster_path
                Picasso.get().load(pathOfImage).into(binding.movieImage)
                binding.titleTv.text = title
                binding.descriptionTv.text = overview
                binding.itemContainer.setOnClickListener { onItemClick(this) }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}

class MoviesViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)