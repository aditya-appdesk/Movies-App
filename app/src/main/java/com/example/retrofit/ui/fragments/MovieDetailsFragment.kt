package com.example.retrofit.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.retrofit.databinding.FragmentMovieDetailsBinding
import com.example.retrofit.utils.ApiResponse
import com.example.retrofit.utils.Constants
import com.example.retrofit.viewmodels.MoviesViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    var binding: FragmentMovieDetailsBinding? = null
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = args.id
        getDataFromVieModel(movieId)
    }

    private fun getDataFromVieModel(movieId: Int) {
        binding!!.progressBar.isGone = false
        viewModel.getMovieDetails(movieId)
        viewModel.singleMoviesDetailLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Success -> {
                    it.data!!.apply {
                        val pathOfImage = Constants.IMAGE_URL + this.poster_path
                        binding!!.movieName.text = this.title
                        binding!!.movieOverView.text = this.overview
                        Picasso.get().load(pathOfImage).into(binding!!.movieNamePoster)
                        binding!!.progressBar.isGone = true
                    }
                }
                is ApiResponse.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    binding!!.progressBar.isGone = true

                }
                is ApiResponse.Loading -> {
                    binding!!.progressBar.isGone = false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}