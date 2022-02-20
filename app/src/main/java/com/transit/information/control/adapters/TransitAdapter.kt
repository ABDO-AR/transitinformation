package com.transit.information.control.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.transit.information.R
import com.transit.information.databinding.TransitItemViewBinding
import com.transit.information.model.Transit

class TransitAdapter(private val context: Context, private val transit: Transit) : RecyclerView.Adapter<TransitAdapter.TransitViewHolder>() {

    // Adapter:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransitViewHolder {
        // Initializing:
        val inflater = LayoutInflater.from(context)
        val binding = TransitItemViewBinding.inflate(inflater, parent, false)
        // Returning:
        return TransitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransitViewHolder, position: Int) {
        // Initializing:
        val entity = transit.entities[position]
        // Prepare:
        holder.binding.apply {
            // Setting:
            routeNumberTextView.text = context.getString(R.string.route_number, entity.tripUpdate.trip.routeId)
            etaTextView.text = entity.tripUpdate.trip.startDate
        }
    }

    override fun getItemCount(): Int = transit.entities.size

    // Holder:
    inner class TransitViewHolder(val binding: TransitItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}