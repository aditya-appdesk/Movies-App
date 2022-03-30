package com.example.retrofit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.adapters.MoviesAdapter
import com.example.retrofit.databinding.FragmentTopBinding
import com.example.retrofit.di.MyApplication
import com.example.retrofit.models.Result
import com.example.retrofit.utill.Constants
import com.example.retrofit.utill.isConnected
import com.example.retrofit.utill.toast
import com.example.retrofit.viewmodels.MoviesViewModel
import com.example.retrofit.viewmodels.ViewModelFactory

class TopFragment : Fragment() {
    private lateinit var binding: FragmentTopBinding
    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory((context?.applicationContext as MyApplication).repo)
        )[MoviesViewModel::class.java]

        if (requireContext().isConnected()) {
            viewModel.getTopMovie(Constants.apiKey)
            viewModel.topMovies.observe(viewLifecycleOwner) {
                binding.recyclerView.layoutManager = LinearLayoutManager(context)
                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    )
                )
                binding.recyclerView.adapter = MoviesAdapter(it, { chageFrag(it) })
            }
        } else {
            requireContext().toast("Internet Not Connected")
        }
    }

    private fun chageFrag(result: Result) {
        findNavController().navigate(
            TopFragmentDirections.actionTopFragmentToMovieDetailsFragment(
                result.id
            )
        )
    }
}