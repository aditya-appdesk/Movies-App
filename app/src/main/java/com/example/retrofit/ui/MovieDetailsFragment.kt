package com.example.retrofit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.retrofit.databinding.FragmentMovieDetailsBinding
import com.example.retrofit.utill.Constants
import com.example.retrofit.viewmodels.MoviesViewModel


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
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        val id = args.id
        viewModel.getSingleMovie(id.toInt(), Constants.apiKey)
        viewModel.singleMoviesDetail.observe(viewLifecycleOwner) {
            binding.movieName.append(it.title)
            binding.movieStory.append(it.overview)
            binding.releaseDate.append(it.release_date)
        }
    }
}