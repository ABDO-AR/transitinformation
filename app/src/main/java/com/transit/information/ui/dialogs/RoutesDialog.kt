package com.transit.information.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.transit.information.control.adapters.RoutesAdapter
import com.transit.information.databinding.DialogRoutesBinding
import com.transit.information.model.Route

class RoutesDialog(private val routes: List<Route>) : BottomSheetDialogFragment() {

    // Fields:
    private lateinit var binding: DialogRoutesBinding

    // Adapter:
    private lateinit var adapter: RoutesAdapter

    // Method(OnCreateView):
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Initializing:
        binding = DialogRoutesBinding.inflate(inflater, container, false)
        // Returning:
        return binding.root
    }

    // Method(OnViewCreated):
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Super:
        super.onViewCreated(view, savedInstanceState)
        // Initializing:
        adapter = RoutesAdapter(requireContext(), routes as ArrayList<Route>)
        // Setting:
        binding.transitsRecyclerView.adapter = adapter
    }
}