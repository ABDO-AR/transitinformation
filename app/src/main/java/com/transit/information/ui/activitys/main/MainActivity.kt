@file:Suppress("SameParameterValue")

package com.transit.information.ui.activitys.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.transit.information.control.adapters.StopsAdapter
import com.transit.information.control.intentions.MainIntentions
import com.transit.information.control.states.MainViewStates
import com.transit.information.databinding.ActivityMainBinding
import com.transit.information.model.Stop
import com.transit.information.ui.dialogs.RoutesDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // Fields:
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    @Suppress("unused")
    private val model: MainViewModel by viewModels()

    // Adapter:
    private lateinit var adapter: StopsAdapter

    // Handler:
    private lateinit var appHandler: Handler

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
        // Initializing:
        appHandler = Handler(Looper.getMainLooper())
        // Rendering:
        rendering()
    }

    // Method(Rendering):
    @Suppress("SameParameterValue")
    private fun rendering() {
        // Method(Received):
        fun received(transit: List<Stop>) {
            // Initializing:
            adapter = StopsAdapter(this, transit, ::onClick)
            // Setting:
            binding.transitsRecyclerView.adapter = adapter
        }
        // Processing:
        lifecycleScope.launchWhenCreated {
            // Every(1M):
            runEvery(70000) {
                // Sending:
                model.apiChannel.send(MainIntentions.GetAPI)
                // Toggling:
                toggle(false)
            }
            // Collecting:
            model.state.collect {
                // Checking:
                when (it) {
                    // Is:
                    is MainViewStates.APIFailure -> Log.d(TAG, "rendering: ${it.error}")
                    is MainViewStates.APIReceived -> toggle(true).also { _ -> received(it.transit.dublinBusStops) }
                    // Else:
                    else -> Log.d(TAG, "rendering: Nothing!")
                }
            }
        }
    }

    // Method(OnClick):
    private fun onClick(stop: Stop) {
        // Initializing:
        val dialog = RoutesDialog(stop.routes)
        // Showing:
        dialog.show(supportFragmentManager, "RoutesDialog")
    }

    // Method(RunEvery):
    private suspend fun runEvery(delay: Long, method: suspend () -> Unit) {
        // Running:
        appHandler.post(object : Runnable {
            // Method(Run):
            override fun run() {
                // Running:
                lifecycleScope.launchWhenCreated { method() }
                // Delaying:
                appHandler.postDelayed(this, delay)
            }
        })
    }

    // Method(Toggle):
    private fun toggle(visible: Boolean = false) {
        // Toggling:
        binding.progress.visibility = if (!visible) View.VISIBLE else View.GONE
        binding.transitsRecyclerView.visibility = if (visible) View.VISIBLE else View.GONE
    }
}