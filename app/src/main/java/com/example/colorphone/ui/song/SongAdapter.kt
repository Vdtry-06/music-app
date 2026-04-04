package com.example.colorphone.ui.song

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.colorphone.R
import com.example.colorphone.databinding.ItemSongBinding

class SongAdapter : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private val lyrics = listOf(
        LyricLine("You never look at the sky", false),
        LyricLine("Cause you think it's too high", false),
        LyricLine("You never look at the stars", false),
        LyricLine("Cause you think they're too far", true),
        LyricLine("But they're showing the lights to the way back home", true),
        LyricLine("When you don't know where to go", true)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSongBinding.inflate(inflater, parent, false)
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(lyrics[position])
    }

    override fun getItemCount(): Int = lyrics.size

    class SongViewHolder(
        private val binding: ItemSongBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LyricLine) {
            binding.tvLyric.text = item.text
            val colorRes = if (item.isMuted) R.color.text_muted else R.color.text_primary
            binding.tvLyric.setTextColor(ContextCompat.getColor(binding.root.context, colorRes))
        }
    }

    data class LyricLine(
        val text: String,
        val isMuted: Boolean
    )
}
