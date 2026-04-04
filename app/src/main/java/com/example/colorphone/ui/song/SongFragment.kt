package com.example.colorphone.ui.song

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.colorphone.databinding.FragmentSongBinding
import com.example.colorphone.ui.core.base.BaseFragment

class SongFragment : BaseFragment<FragmentSongBinding>(FragmentSongBinding::inflate) {

    private val viewModel: SongViewModel by viewModels()
    private val songAdapter by lazy { SongAdapter() }

    override fun setupView() {
        binding.rvLyrics.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = songAdapter
        }
    }
}
