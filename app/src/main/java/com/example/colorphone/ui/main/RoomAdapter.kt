package com.example.colorphone.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.colorphone.R
import com.example.colorphone.databinding.ItemRoomBinding
import com.example.colorphone.domain.model.Room
import com.example.colorphone.domain.model.RoomStatus
import java.text.NumberFormat
import java.util.Locale

class RoomAdapter(
    private val onLongClick: (Room) -> Unit
) : ListAdapter<Room, RoomAdapter.RoomViewHolder>(RoomDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RoomViewHolder(private val binding: ItemRoomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(room: Room) {
            binding.apply {
                tvRoomName.text = room.name
                
                // Format price
                val format = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
                tvPrice.text = format.format(room.price)
                
                // Set status text and background color
                tvStatus.text = room.status.description
                if (room.status == RoomStatus.AVAILABLE) {
                    tvStatus.background = ContextCompat.getDrawable(root.context, R.drawable.bg_status_available)
                } else {
                    tvStatus.background = ContextCompat.getDrawable(root.context, R.drawable.bg_status_rented)
                }

                root.setOnLongClickListener {
                    onLongClick(room)
                    true
                }
            }
        }
    }

    class RoomDiffCallback : DiffUtil.ItemCallback<Room>() {
        override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
            return oldItem == newItem
        }
    }
}
