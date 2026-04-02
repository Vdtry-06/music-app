package com.example.colorphone.ui.queue

import android.view.View
import com.example.colorphone.databinding.FragmentQueueBinding
import com.example.colorphone.domain.model.Song
import com.example.colorphone.ui.core.base.BaseFragment

class QueueFragment : BaseFragment<FragmentQueueBinding>(FragmentQueueBinding::inflate) {

    private val nextInQueueAdapter by lazy { QueueAdapter { updateBottomBarVisibility() } }
    private val nextInPlaylistAdapter by lazy { QueueAdapter { updateBottomBarVisibility() } }

    private val nextInQueueList = listOf(
        Song(1, "Beguille", "NIKI"),
        Song(2, "Stardust", "Nyx"),
        Song(3, "Forever", "moody.")
    )

    private val nextInPlaylistList = listOf(
        Song(4, "Coffee", "Kainbeats"),
        Song(5, "raindrops", "rainyyxx"),
        Song(6, "Tokyo", "SmYang"),
        Song(7, "Lullaby", "iamfinenow"),
        Song(8, "Hazel Eyes", "moody."),
        Song(9, "Sleepy", "neeeeni")
    )

    override fun setupView() {
        super.setupView()
        binding.rvNextInQueue.adapter = nextInQueueAdapter
        binding.rvNextInPlaylist.adapter = nextInPlaylistAdapter

        nextInQueueAdapter.submitList(nextInQueueList)
        nextInPlaylistAdapter.submitList(nextInPlaylistList)
    }

    private fun updateBottomBarVisibility() {
        val anySelectedInQueue = nextInQueueAdapter.currentList.any { it.isSelected }
        val anySelectedInPlaylist = nextInPlaylistAdapter.currentList.any { it.isSelected }

        if (anySelectedInQueue || anySelectedInPlaylist) {
            binding.bottomBar.visibility = View.VISIBLE
        } else {
            binding.bottomBar.visibility = View.GONE
        }
    }
}
