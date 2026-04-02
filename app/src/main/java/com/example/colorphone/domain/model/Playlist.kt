package com.example.colorphone.domain.model

data class Playlist(
    val id: Int,
    val name: String,
    val songCount: Int,
    val imageUrl: String? = null
)
