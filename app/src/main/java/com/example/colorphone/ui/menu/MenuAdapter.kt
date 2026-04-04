package com.example.colorphone.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.colorphone.R
import com.example.colorphone.databinding.ItemMenuBinding

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    private val items = listOf(
        MenuItem(R.drawable.ic_menu_playlist, R.string.menu_add_to_playlist),
        MenuItem(R.drawable.ic_menu_queue, R.string.menu_add_to_queue),
        MenuItem(R.drawable.ic_menu_remove, R.string.menu_remove_from_playlist),
        MenuItem(R.drawable.ic_menu_tag, R.string.menu_modify_tags),
        MenuItem(R.drawable.ic_menu_artist, R.string.menu_view_artist),
        MenuItem(R.drawable.ic_menu_album, R.string.menu_view_album),
        MenuItem(R.drawable.ic_menu_credits, R.string.menu_show_credits),
        MenuItem(R.drawable.ic_download_song, R.string.content_download),
        MenuItem(R.drawable.ic_share_song, R.string.menu_share),
        MenuItem(R.drawable.ic_menu_qr, R.string.menu_generate_qr_code),
        MenuItem(R.drawable.ic_menu_sleep, R.string.menu_sleep_timer),
        MenuItem(R.drawable.ic_menu_hide, R.string.menu_hide_song),
        MenuItem(R.drawable.ic_menu_radio, R.string.menu_go_to_song_radio)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMenuBinding.inflate(inflater, parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class MenuViewHolder(
        private val binding: ItemMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MenuItem) {
            binding.ivIcon.setImageResource(item.iconRes)
            binding.tvTitle.setText(item.titleRes)
        }
    }

    data class MenuItem(
        val iconRes: Int,
        val titleRes: Int
    )
}
