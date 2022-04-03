package com.example.retrofit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.retrofit.databinding.FragmentMovieDetailsBinding
import com.example.retrofit.di.MyApplication
import com.example.retrofit.utill.Constants
import com.example.retrofit.viewmodels.MovieViewModelFactory
import com.example.retrofit.viewmodels.MoviesViewModel
import com.squareup.picasso.Picasso

class MovieDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            MovieViewModelFactory((context?.applicationContext as MyApplication).appContainer.repo)
        )[MoviesViewModel::class.java]

        val id = args.id
        viewModel.getMovieDetails(id)
        viewModel.singleMoviesDetailLiveData.observe(viewLifecycleOwner) {
            val pathOfImage = Constants.IMAGE_URL + it.poster_path
            binding.movieName.text = it.title
            binding.movieOverView.text = it.overview
            Picasso.get().load(pathOfImage).into(binding.movieNamePoster)
        }
    }
}