package com.example.colorphone.ui.playlist

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.colorphone.databinding.FragmentPlaylistBinding
import com.example.colorphone.ui.core.base.BaseFragment

class PlaylistFragment : BaseFragment<FragmentPlaylistBinding>(FragmentPlaylistBinding::inflate) {

    private val viewModel: PlaylistViewModel by viewModels()
    private val playlistAdapter by lazy { PlaylistAdapter() }

    override fun setupView() {
        binding.rvPlaylist.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = playlistAdapter
        }
    }
}
