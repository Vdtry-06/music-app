package com.example.colorphone.ui.menu

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.colorphone.databinding.FragmentMenuBinding
import com.example.colorphone.ui.core.base.BaseFragment

class MenuFragment : BaseFragment<FragmentMenuBinding>(FragmentMenuBinding::inflate) {

    private val viewModel: MenuViewModel by viewModels()
    private val menuAdapter by lazy { MenuAdapter() }

    override fun setupView() {
        binding.rvMenu.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = menuAdapter
        }
    }
}
