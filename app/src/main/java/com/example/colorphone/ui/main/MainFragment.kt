package com.example.colorphone.ui.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.colorphone.R
import com.example.colorphone.databinding.FragmentMainBinding
import com.example.colorphone.ui.core.base.BaseFragment
import kotlinx.coroutines.launch


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    override fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

            }
        }
    }

    override fun setUpListener() {
        super.setUpListener()
        binding.btnGoToAddPlaylist.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_addPlaylistFragment)
        }
        
        binding.btnGoToQueue.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_queueFragment)
        }
    }
}
