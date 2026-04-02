package com.example.colorphone.ui.playlist

import com.example.colorphone.databinding.FragmentAddPlaylistBinding
import com.example.colorphone.domain.model.Playlist
import com.example.colorphone.ui.core.base.BaseFragment

class AddPlaylistFragment : BaseFragment<FragmentAddPlaylistBinding>(FragmentAddPlaylistBinding::inflate) {

    private val playlistAdapter by lazy { PlaylistAdapter() }

    override fun setupView() {
        super.setupView()
        binding.rvPlaylists.adapter = playlistAdapter
        
        // Populate fake data
        val fakePlaylists = listOf(
            Playlist(1, "current favorites", 20),
            Playlist(2, "3:00am vibes", 18),
            Playlist(3, "Lofi Loft", 63),
            Playlist(4, "rain on my window", 32),
            Playlist(5, "Anime OSTs", 20),
            Playlist(6, "Study beats", 45),
            Playlist(7, "Chill Night", 12)
        )
        playlistAdapter.submitList(fakePlaylists)
    }

    override fun setUpListener() {
        super.setUpListener()
        binding.btnBack.setOnClickListener {
            navController.popBackStack()
        }
        
        binding.btnNewPlaylist.setOnClickListener {
            CreatePlaylistDialog.newInstance().show(childFragmentManager, CreatePlaylistDialog.TAG)
        }
    }
}
