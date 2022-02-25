package com.transit.information.control.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.transit.information.R
import com.transit.information.databinding.TransitItemViewBinding
import com.transit.information.model.Route

class RoutesAdapter(private val context: Context, private val routes: ArrayList<Route>) : RecyclerView.Adapter<RoutesAdapter.RoutesViewHolder>() {

    // Adapter:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutesViewHolder {
        // Initializing:
        val inflater = LayoutInflater.from(context)
        val binding = TransitItemViewBinding.inflate(inflater, parent, false)
        // Returning:
        return RoutesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoutesViewHolder, position: Int) {
        // Initializing:
        val route = routes[position]
        // Prepare:
        holder.binding.apply {
            // Setting:
            routeNumberTextView.text = context.getString(R.string.route_number, route.routeShortName)
            etaTextView.text = "${route.time} mins"
        }
    }

    override fun getItemCount(): Int = routes.size

    // Holder:
    inner class RoutesViewHolder(val binding: TransitItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}