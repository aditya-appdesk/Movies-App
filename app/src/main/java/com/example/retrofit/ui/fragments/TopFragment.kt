package com.example.retrofit.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.data.models.Result
import com.example.retrofit.databinding.FragmentTopBinding
import com.example.retrofit.ui.adapters.MoviesAdapter
import com.example.retrofit.utils.ApiResponse
import com.example.retrofit.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopFragment : Fragment() {
    var binding: FragmentTopBinding? = null
    private val viewModel: MoviesViewModel by viewModels()
    lateinit var list: List<Result>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.progressBar.isGone = false
        viewModel.getTopMoviesList()
        viewModel.topMoviesLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiResponse.Success -> {
                    setUpRecyclerView(result.data!!)
                    binding!!.progressBar.isGone = true
                }
                is ApiResponse.Error -> {
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    binding!!.progressBar.isGone = true
                }
                is ApiResponse.Loading -> {
                    binding!!.progressBar.isVisible = true
                }
            }
        }
    }

    // this function is to called to go to the movies details fragment
    private fun changeFragment(result: Result) {
        findNavController().navigate(
            TopFragmentDirections.actionTopFragmentToMovieDetailsFragment(
                result.id
            )
        )
    }

    private fun setUpRecyclerView(list: List<Result>) {
        binding!!.recyclerView.layoutManager = LinearLayoutManager(context)
        binding!!.recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding!!.recyclerView.adapter = MoviesAdapter(list, { changeFragment(it) })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}