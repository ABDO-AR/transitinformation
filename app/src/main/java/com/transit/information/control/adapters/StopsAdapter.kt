package com.transit.information.control.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.transit.information.R
import com.transit.information.databinding.StopItemViewBinding
import com.transit.information.databinding.TransitItemViewBinding
import com.transit.information.model.Stop

class StopsAdapter(private val context: Context, private val stops: List<Stop>, private val onClick: (stop: Stop) -> Unit) : RecyclerView.Adapter<StopsAdapter.StopsViewHolder>() {

    // Adapter:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopsViewHolder {
        // Initializing:
        val inflater = LayoutInflater.from(context)
        val binding = StopItemViewBinding.inflate(inflater, parent, false)
        // Returning:
        return StopsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StopsViewHolder, position: Int) {
        // Initializing:
        val stop = stops[position]
        // Prepare:
        holder.binding.apply {
            // Setting:
            stopNameTextView.text = stop.stopName
            root.setOnClickListener { onClick(stop) }
        }
    }

    override fun getItemCount(): Int = stops.size

    // Holder:
    inner class StopsViewHolder(val binding: StopItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}