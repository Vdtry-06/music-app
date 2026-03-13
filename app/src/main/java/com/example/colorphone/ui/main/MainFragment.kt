package com.example.colorphone.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.colorphone.databinding.FragmentMainBinding
import com.example.colorphone.ui.core.ActivityViewModel
import com.example.colorphone.ui.core.base.BaseFragment
import com.example.colorphone.ui.utils.DialogUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: ActivityViewModel by activityViewModels()
    
    private val adapter by lazy { 
        RoomAdapter { room ->
            showDeleteConfirmDialog(room.id, room.name)
        } 
    }

    override fun setupView() {
        binding.rvRooms.adapter = adapter
        binding.rvRooms.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun showDeleteConfirmDialog(roomId: String, roomName: String) {
        DialogUtils.showConfirmDeleteDialog(requireContext(), roomName) {
            viewModel.deleteRoom(roomId)
        }
    }

    override fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.rooms.collect { rooms ->
                    adapter.submitList(rooms)
                }
            }
        }
    }
}
