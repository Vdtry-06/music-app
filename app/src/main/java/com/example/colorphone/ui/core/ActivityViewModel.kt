package com.example.colorphone.ui.core

import androidx.lifecycle.ViewModel
import com.example.colorphone.domain.model.Room
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor() : ViewModel() {

    private val _rooms = MutableStateFlow<List<Room>>(emptyList())
    val rooms: StateFlow<List<Room>> = _rooms.asStateFlow()

    init {
        // Dummy data
        _rooms.value = listOf(
            Room("R101", "Phòng 101", 1500000.0, com.example.colorphone.domain.model.RoomStatus.AVAILABLE),
            Room("R102", "Phòng 102", 2000000.0, com.example.colorphone.domain.model.RoomStatus.RENTED, "Nguyễn Văn A", "0912345678"),
            Room("R103", "Phòng 103", 1500000.0, com.example.colorphone.domain.model.RoomStatus.AVAILABLE),
            Room("R104", "Phòng 104", 3000000.0, com.example.colorphone.domain.model.RoomStatus.RENTED, "Trần Thị B", "0987654321"),
            Room("R105", "Phòng 105", 2500000.0, com.example.colorphone.domain.model.RoomStatus.AVAILABLE)
        )
    }

    // Create
    fun addRoom(room: Room) {
        _rooms.update { currentList ->
            currentList + room
        }
    }

    // Read (Get specific room, list is already exposed via StateFlow)
    fun getRoom(roomId: String): Room? {
        return _rooms.value.find { it.id == roomId }
    }

    // Update
    fun updateRoom(updatedRoom: Room) {
        _rooms.update { currentList ->
            currentList.map { 
                if (it.id == updatedRoom.id) updatedRoom else it 
            }
        }
    }

    // Delete
    fun deleteRoom(roomId: String) {
        _rooms.update { currentList ->
            currentList.filterNot { it.id == roomId }
        }
    }
}