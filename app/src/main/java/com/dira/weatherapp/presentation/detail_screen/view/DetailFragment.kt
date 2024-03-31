package com.dira.weatherapp.presentation.detail_screen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dira.weatherapp.databinding.FragmentDetailBinding
import com.dira.weatherapp.util.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun setupView() {

    }
}