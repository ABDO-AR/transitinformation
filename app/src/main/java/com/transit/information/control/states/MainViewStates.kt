package com.transit.information.control.states

import com.transit.information.model.Stop

sealed class MainViewStates {

    // State(Nothing):
    object Nothing : MainViewStates()

    // State(APIReceived):
    data class APIFailure(val error: String) : MainViewStates()

    // State(APIReceived):
    data class APIReceived(val stops: List<Stop>) : MainViewStates()
}
