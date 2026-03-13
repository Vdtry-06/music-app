package com.example.colorphone.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.colorphone.R
import com.example.colorphone.databinding.FragmentMainBinding
import com.example.colorphone.ui.core.ActivityViewModel
import com.example.colorphone.ui.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: ActivityViewModel by activityViewModels()
    private val adapter by lazy { 
        RoomAdapter { room ->
            val action = MainFragmentDirections.actionMainFragmentToEditRoomFragment2(room.id)
            findNavController().navigate(action)
        } 
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvRooms.adapter = adapter
        binding.rvRooms.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun setUpListener() {
        binding.fabAddRoom.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_addRoomFragment)
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
