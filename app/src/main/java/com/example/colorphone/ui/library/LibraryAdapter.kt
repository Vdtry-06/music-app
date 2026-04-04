package com.example.colorphone.ui.library

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.colorphone.R
import com.example.colorphone.databinding.ItemLibraryListBinding
import com.example.colorphone.domain.model.LibraryItem
import com.example.colorphone.domain.model.LibraryItemType
import com.google.android.material.shape.ShapeAppearanceModel

class LibraryAdapter(
    private val onItemClick: (LibraryItem) -> Unit
) : ListAdapter<LibraryItem, LibraryAdapter.LibraryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val binding = ItemLibraryListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LibraryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LibraryViewHolder(private val binding: ItemLibraryListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LibraryItem) {
            binding.apply {
                tvTitle.text = item.title
                tvSubtitle.text = item.subtitle

                // Handle Image Shape and Content
                val shapeAppearance = if (item.type == LibraryItemType.ARTIST) {
                    R.style.CircleAppearance
                } else {
                    R.style.SquareAppearance
                }
                
                ivItem.shapeAppearanceModel = ShapeAppearanceModel.builder(
                    root.context,
                    shapeAppearance,
                    0
                ).build()

                if (item.imageUrl != null) {
                    ivItem.load(item.imageUrl)
                } else {
                    val placeholder = when (item.type) {
                        LibraryItemType.FOLDER -> R.drawable.ic_folder
                        else -> R.drawable.ic_music_note
                    }
                    ivItem.setImageResource(placeholder)
                }

                root.setOnClickListener { onItemClick(item) }
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<LibraryItem>() {
        override fun areItemsTheSame(oldItem: LibraryItem, newItem: LibraryItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LibraryItem, newItem: LibraryItem): Boolean {
            return oldItem == newItem
        }
    }
}
