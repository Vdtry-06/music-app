package com.example.colorphone.ui.sleep_timer

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.colorphone.databinding.FragmentSleepTimerBinding
import com.example.colorphone.ui.core.base.BaseFragment

class SleepTimerFragment :
    BaseFragment<FragmentSleepTimerBinding>(FragmentSleepTimerBinding::inflate) {

    private val viewModel: SleepTimerViewModel by viewModels()
    private val sleepTimerAdapter by lazy { SleepTimerAdapter() }

    override fun setupView() {
        binding.rvSleepTimer.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = sleepTimerAdapter
        }
    }
}
