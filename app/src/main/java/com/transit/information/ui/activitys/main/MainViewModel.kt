@file:Suppress("SENSELESS_COMPARISON")

package com.transit.information.ui.activitys.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transit.information.control.intentions.MainIntentions
import com.transit.information.control.repositories.MainRepository
import com.transit.information.control.states.MainViewStates
import com.transit.information.model.Stop
import com.transit.information.model.Transit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    // Channels:
    val apiChannel: Channel<MainIntentions> = Channel(Channel.UNLIMITED)

    // States:
    private val _state: MutableStateFlow<MainViewStates> = MutableStateFlow(MainViewStates.Nothing)
    val state: StateFlow<MainViewStates> = _state

    // Initializing:
    init {
        // Processing:
        intentionsProcessing()
    }

    // Method(IntentionsProcessing):
    private fun intentionsProcessing() {
        // Processing:
        viewModelScope.launch {
            // Reducing:
            apiChannel.consumeAsFlow().collect { statesReducing(it) }
        }
    }

    // Method(StatesReducing):
    private fun statesReducing(intentions: MainIntentions) {
        // Method(getAPI):
        suspend fun getAPI() {
            // Initializing:
            val call = repository.getAPI()
            // Enqueue:
            call.enqueue(object : Callback<Transit> {
                // Method(OnResponse):
                override fun onResponse(call: Call<Transit>, response: Response<Transit>) {
                    // Processing:
                    viewModelScope.launch {
                        // Initializing:
                        val transit = response.body()
                        // Checking:
                        if (response.isSuccessful && transit!!.dublinBusStops != null) {
                            // Checking:
                            if (transit.dublinBusStops.isNotEmpty()) _state.emit(MainViewStates.APIReceived(transit))
                            else _state.emit(MainViewStates.APIFailure("OnResponse: Body is Empty!"))
                        } else _state.emit(MainViewStates.APIFailure("OnResponse: Body is null!"))
                    }
                }

                // Method(OnFailure):
                override fun onFailure(call: Call<Transit>, t: Throwable) {
                    // Emitting:
                    viewModelScope.launch { _state.emit(MainViewStates.APIFailure(t.message!!)) }
                }
            })
        }
        // Reducing:
        viewModelScope.launch {
            // Checking:
            when (intentions) {
                // Checking:
                is MainIntentions.GetAPI -> getAPI()
            }
        }
    }
}