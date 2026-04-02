package com.example.colorphone.ui.queue

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.colorphone.R
import com.example.colorphone.databinding.ItemSongQueueBinding
import com.example.colorphone.domain.model.Song

class QueueAdapter(private val onSelectionChanged: () -> Unit) : ListAdapter<Song, QueueAdapter.QueueViewHolder>(SongDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueueViewHolder {
        val binding = ItemSongQueueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QueueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QueueViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class QueueViewHolder(private val binding: ItemSongQueueBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            binding.tvSongTitle.text = song.title
            binding.tvArtist.text = song.artist
            
            val iconRes = if (song.isSelected) R.drawable.ic_circle_filled else R.drawable.ic_circle_outline
            binding.ivSelect.setImageResource(iconRes)
            binding.ivSelect.setColorFilter(
                binding.root.context.getColor(if (song.isSelected) R.color.cyan else R.color.gray_text)
            )

            binding.root.setOnClickListener {
                song.isSelected = !song.isSelected
                notifyItemChanged(adapterPosition)
                onSelectionChanged()
            }
        }
    }

    class SongDiffCallback : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.isSelected == newItem.isSelected && oldItem.title == newItem.title
        }
    }
}
