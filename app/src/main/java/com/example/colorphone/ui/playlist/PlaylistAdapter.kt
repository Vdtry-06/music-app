package com.example.colorphone.ui.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.colorphone.R
import com.example.colorphone.databinding.ItemPlaylistBinding

class PlaylistAdapter : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    private val items = listOf(
        PlaylistItem("grainy days", "moody.", R.drawable.bg_thumb_grainy, false),
        PlaylistItem("Coffee", "Kainbeats", R.drawable.bg_thumb_coffee, false),
        PlaylistItem("raindrops", "rainyyxx", R.drawable.bg_thumb_rain, false),
        PlaylistItem("Tokyo", "SmYang", R.drawable.bg_thumb_tokyo, false),
        PlaylistItem("Lullaby", "iamfinenow", R.drawable.bg_thumb_lullaby, false),
        PlaylistItem("Hazel Eyes", "moody.", R.drawable.bg_thumb_hazel, true)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPlaylistBinding.inflate(inflater, parent, false)
        return PlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class PlaylistViewHolder(
        private val binding: ItemPlaylistBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PlaylistItem) {
            binding.ivThumb.setBackgroundResource(item.thumbnailRes)
            binding.tvTitle.text = item.title
            binding.tvArtist.text = item.artist

            val titleColor = if (item.isMuted) R.color.text_muted else R.color.text_primary
            val subtitleColor = if (item.isMuted) R.color.text_muted else R.color.text_secondary
            val alpha = if (item.isMuted) 0.55f else 1f

            binding.tvTitle.setTextColor(binding.root.context.getColor(titleColor))
            binding.tvArtist.setTextColor(binding.root.context.getColor(subtitleColor))
            binding.ivMore.alpha = alpha
            binding.ivThumb.contentDescription = item.title
        }
    }

    data class PlaylistItem(
        val title: String,
        val artist: String,
        val thumbnailRes: Int,
        val isMuted: Boolean
    )
}
