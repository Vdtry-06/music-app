package com.example.colorphone.ui.sleep_timer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.colorphone.R
import com.example.colorphone.databinding.ItemSleepTimerBinding

class SleepTimerAdapter : RecyclerView.Adapter<SleepTimerAdapter.SleepTimerViewHolder>() {

    private val items = listOf(
        R.string.sleep_timer_end_of_track,
        R.string.sleep_timer_60_seconds,
        R.string.sleep_timer_5_minutes,
        R.string.sleep_timer_10_minutes,
        R.string.sleep_timer_15_minutes,
        R.string.sleep_timer_30_minutes,
        R.string.sleep_timer_1_hour
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepTimerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSleepTimerBinding.inflate(inflater, parent, false)
        return SleepTimerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SleepTimerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class SleepTimerViewHolder(
        private val binding: ItemSleepTimerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(titleRes: Int) {
            binding.tvTitle.setText(titleRes)
        }
    }
}
