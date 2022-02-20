@file:Suppress("SameParameterValue")

package com.transit.information.ui.activitys.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.transit.information.control.adapters.TransitAdapter
import com.transit.information.control.intentions.MainIntentions
import com.transit.information.control.states.MainViewStates
import com.transit.information.databinding.ActivityMainBinding
import com.transit.information.model.Transit
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // Fields:
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    @Suppress("unused")
    private val model: MainViewModel by viewModels()

    // Adapter:
    private lateinit var adapter: TransitAdapter

    // Companion:
    companion object {
        // Tags:
        @Suppress("unused")
        const val TAG: String = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Super:
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO).also { setContentView(binding.root) }
        // Rendering:
        rendering()
    }

    // Method(Rendering):
    @Suppress("SameParameterValue")
    private fun rendering() {
        // Method(Received):
        fun received(transit: Transit) {
            // Initializing:
            adapter = TransitAdapter(this, transit)
            // Setting:
            binding.transitsRecyclerView.adapter = adapter
        }
        // Processing:
        lifecycleScope.launchWhenCreated {
            // Sending:
            model.apiChannel.send(MainIntentions.GetAPI)
            // Collecting:
            model.state.collect {
                // Checking:
                when (it) {
                    // Is:
                    is MainViewStates.APIFailure -> Log.d(TAG, "rendering: ${it.error}")
                    is MainViewStates.APIReceived -> toggle(true).also { _ -> received(it.transit) }
                    // Else:
                    else -> Log.d(TAG, "rendering: Nothing!")
                }
            }
        }
    }

    // Method(Toggle):
    private fun toggle(visible: Boolean = false) {
        // Toggling:
        binding.progress.visibility = if (!visible) View.VISIBLE else View.GONE
        binding.transitsRecyclerView.visibility = if (visible) View.VISIBLE else View.GONE
    }
}