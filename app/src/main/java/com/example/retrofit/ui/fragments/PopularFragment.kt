package com.example.retrofit.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.R
import com.example.retrofit.data.models.Result
import com.example.retrofit.databinding.FragmentPopulourBinding
import com.example.retrofit.ui.adapters.MoviesAdapter
import com.example.retrofit.utils.ApiResponse
import com.example.retrofit.utils.isConnected
import com.example.retrofit.utils.toast
import com.example.retrofit.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment : Fragment() {
    private var _binding: FragmentPopulourBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by viewModels()
    var movieAdapter: MoviesAdapter? = null
    private val dataList = mutableListOf<Result>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopulourBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        if (requireContext().isConnected()) {
            getDataFromViewModel()
        } else {
            requireContext().toast("No Internet Connection")
        }
        observePopularMovies()
    }

    fun observePopularMovies(){
        viewModel._popularMovieLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiResponse.Success -> {
                    result.data?.let { dataList.addAll(it) }
                    setUpRecyclerView()
                    binding.progressBar.isGone = true
                }
                is ApiResponse.Error -> {
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.isGone = true

                }
                is ApiResponse.Loading -> {
                    binding.progressBar.isGone = false
                }
            }
        }
    }

    private fun getDataFromViewModel() {
        binding.progressBar.isGone = false
        viewModel.getPopularMoviesList()
    }

    // this function is to called to go to the movies details fragment
    private fun changeFragment(result: Result) {
        findNavController().navigate(
            PopularFragmentDirections.actionPopulourFragmentToMovieDetailsFragment(
                result.id
            )
        )
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    )
                )
                movieAdapter = MoviesAdapter(dataList) { changeFragment(it) }
                adapter = movieAdapter
            } else {
                adapter?.notifyDataSetChanged()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.refresh) {
            dataList.clear()
            movieAdapter?.notifyDataSetChanged()
            getDataFromViewModel()
            requireContext().toast("Items Refreshed")
        }
        return super.onOptionsItemSelected(item)
    }
}