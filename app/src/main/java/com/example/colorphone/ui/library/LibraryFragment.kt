package com.example.colorphone.ui.library

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.colorphone.R
import com.example.colorphone.databinding.FragmentLibraryBinding
import com.example.colorphone.domain.model.LibraryItemType
import com.example.colorphone.ui.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LibraryFragment : BaseFragment<FragmentLibraryBinding>(FragmentLibraryBinding::inflate) {

    private val viewModel: LibraryViewModel by viewModels()
    private lateinit var adapter: LibraryAdapter

    override fun setupView() {
        super.setupView()

        // Setup Header and Actions
        setupHeaderActions()

        // Setup RecyclerView
        adapter = LibraryAdapter { item ->
            // Handle item click (e.g., navigate to detail)
        }
        binding.rvLibrary.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@LibraryFragment.adapter
        }
    }

    override fun setUpListener() {
        super.setUpListener()

        // Header and Search buttons
        binding.btnSearch.setOnClickListener {
            // Handle Search
        }

        // Action Buttons
        binding.itemAddPlaylist.root.setOnClickListener {
            // New Playlist
        }
        binding.itemLikedSongs.root.setOnClickListener {
            // Liked Songs
        }

        // Chip selection logic
        binding.chipGroupFilters.setOnCheckedStateChangeListener { group, checkedIds ->
            val filter = when (checkedIds.firstOrNull()) {
                R.id.chipFolders -> LibraryItemType.FOLDER
                R.id.chipPlaylists -> LibraryItemType.PLAYLIST
                R.id.chipArtists -> LibraryItemType.ARTIST
                R.id.chipAlbums -> LibraryItemType.ALBUM
                R.id.chipPodcasts -> LibraryItemType.PODCAST
                else -> null
            }
            viewModel.setFilter(filter)
        }
    }

    override fun setupObserver() {
        super.setupObserver()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.libraryItems.collectLatest { items ->
                    adapter.submitList(items)
                    // Toggle visibility of actions based on filters
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentFilter.collectLatest { filter ->
                    // Visibility logic for fixed items: Only show in "All" view (null filter)
                    val visibility = if (filter == null) View.VISIBLE else View.GONE
                    binding.layoutActions.visibility = visibility
                    binding.layoutSort.visibility = visibility
                }
            }
        }
    }

    private fun setupHeaderActions() {
        // "Add New Playlist" (plus icon on cyan background)
        binding.itemAddPlaylist.apply {
            ivItem.setImageResource(R.drawable.ic_plus)
            ivItem.setBackgroundColor(resources.getColor(R.color.cyan_accent, null))
            tvTitle.text = getString(R.string.add_new_playlist)
            tvSubtitle.visibility = View.GONE
        }

        // "Your Liked Songs" (heart icon on cyan background)
        binding.itemLikedSongs.apply {
            ivItem.setImageResource(R.drawable.ic_heart)
            ivItem.setBackgroundColor(resources.getColor(R.color.cyan_accent, null))
            tvTitle.text = getString(R.string.liked_songs)
            tvSubtitle.visibility = View.GONE
        }
    }
}
