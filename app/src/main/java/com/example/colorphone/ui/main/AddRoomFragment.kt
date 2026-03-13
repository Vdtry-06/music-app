package com.example.colorphone.ui.main

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.colorphone.databinding.FragmentAddRoomBinding
import com.example.colorphone.domain.model.Room
import com.example.colorphone.domain.model.RoomStatus
import com.example.colorphone.ui.core.ActivityViewModel
import com.example.colorphone.ui.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddRoomFragment : BaseFragment<FragmentAddRoomBinding>(FragmentAddRoomBinding::inflate) {

    private val viewModel: ActivityViewModel by activityViewModels()

    override fun setUpListener() {
        binding.btnAddRoom.setOnClickListener {
            validateAndAddRoom()
        }
    }

    private fun validateAndAddRoom() {
        val id = binding.etRoomId.text.toString().trim()
        val name = binding.etRoomName.text.toString().trim()
        val priceStr = binding.etPrice.text.toString().trim()
        val status = if (binding.rbAvailable.isChecked) RoomStatus.AVAILABLE else RoomStatus.RENTED

        if (id.isEmpty()) {
            binding.tilRoomId.error = "Vui lòng nhập mã phòng"
            return
        } else {
            binding.tilRoomId.error = null
        }

        if (name.isEmpty()) {
            binding.tilRoomName.error = "Vui lòng nhập tên phòng"
            return
        } else {
            binding.tilRoomName.error = null
        }

        val price = priceStr.toDoubleOrNull()
        if (price == null || price <= 0) {
            binding.tilPrice.error = "Vui lòng nhập giá hợp lệ"
            return
        } else {
            binding.tilPrice.error = null
        }

        val newRoom = Room(
            id = id,
            name = name,
            price = price,
            status = status
        )

        viewModel.addRoom(newRoom)
        Toast.makeText(requireContext(), "Thêm phòng thành công", Toast.LENGTH_SHORT).show()
        navController.popBackStack()
    }
}