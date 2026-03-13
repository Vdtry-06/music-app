package com.example.colorphone.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.colorphone.databinding.FragmentEditRoomBinding
import com.example.colorphone.domain.model.Room
import com.example.colorphone.domain.model.RoomStatus
import com.example.colorphone.ui.core.ActivityViewModel
import com.example.colorphone.ui.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditRoomFragment : BaseFragment<FragmentEditRoomBinding>(FragmentEditRoomBinding::inflate) {

    private val viewModel: ActivityViewModel by activityViewModels()
    private val args: EditRoomFragmentArgs by navArgs()
    private var currentRoom: Room? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val roomId = args.roomId
        currentRoom = viewModel.getRoom(roomId)

        currentRoom?.let { room ->
            setupUI(room)
        }

        binding.btnSave.setOnClickListener {
            updateRoom()
        }
    }

    private fun setupUI(room: Room) {
        binding.apply {
            etRoomName.setText(room.name)
            etPrice.setText(room.price.toString())
            etTenantName.setText(room.tenantName)
            etPhoneNumber.setText(room.phoneNumber)
            if (room.status == RoomStatus.AVAILABLE) {
                rbAvailable.isChecked = true
            } else {
                rbRented.isChecked = true
            }
        }
    }

    private fun updateRoom() {
        val name = binding.etRoomName.text.toString()
        val price = binding.etPrice.text.toString().toDoubleOrNull() ?: 0.0
        val tenantName = binding.etTenantName.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val status = if (binding.rbAvailable.isChecked) RoomStatus.AVAILABLE else RoomStatus.RENTED

        currentRoom?.let { room ->
            val updatedRoom = room.copy(
                name = name,
                price = price,
                status = status,
                tenantName = tenantName,
                phoneNumber = phoneNumber
            )
            viewModel.updateRoom(updatedRoom)
            findNavController().popBackStack()
        }
    }

    override fun setupObserver() {
    }
}
