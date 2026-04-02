package com.example.colorphone.domain.model

data class Song(
    val id: Int,
    val title: String,
    val artist: String,
    val imageUrl: String? = null,
    var isSelected: Boolean = false
)
