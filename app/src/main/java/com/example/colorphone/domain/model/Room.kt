package com.example.colorphone.domain.model

data class Room(
    val id: String,
    val name: String,
    val price: Double,
    val status: RoomStatus,
    val tenantName: String? = null,
    val phoneNumber: String? = null
)

enum class RoomStatus(val description: String) {
    AVAILABLE("Còn trống"),
    RENTED("Đã thuê")
}
