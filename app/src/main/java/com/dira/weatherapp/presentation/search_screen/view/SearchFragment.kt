package com.dira.weatherapp.presentation.search_screen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.dira.weatherapp.R
import com.dira.weatherapp.databinding.FragmentSearchBinding
import com.dira.weatherapp.presentation.detail_screen.view.DetailFragmentSearch
import com.dira.weatherapp.presentation.search_screen.view_model.SearchViewModel
import com.dira.weatherapp.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun setupView() {
        binding.btnSearch.setOnClickListener {
            val inputCity = binding.inputCity.text.toString()
            viewModel.getSearchWeater(inputCity)
            viewModel.serachWeather.observe(viewLifecycleOwner){it ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, DetailFragmentSearch(it))
                    //.addToBackStack(null) // tambahkan backstage agar bisa kembali ke fragment sebelumnya
                    .commit()
            }
        }
    }

}