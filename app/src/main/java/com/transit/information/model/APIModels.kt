@file:Suppress("unused")

package com.transit.information.model

// Data(Class):
data class Transit(
    // Fields:
    val entities: List<Entity>,
    val header: Header,
    var dublinBusStops: List<Stop> = ArrayList()
)

// Data(Class):
data class Arrival(
    // Fields:
    val delay: Int,
    val time: Int
)

// Data(Class):
data class Departure(
    // Fields:
    val delay: Int,
    val time: Int
)

// Data(Class):
data class Entity(
    // Fields:
    val id: String,
    val isDeleted: Boolean,
    val tripUpdate: TripUpdate
)

// Data(Class):
data class Header(
    // Fields:
    val gtfsRealtimeVersion: String,
    val incrementality: String,
    val timestamp: Int
)

// Data(Class):
data class StopTimeUpdate(
    // Fields:
    val arrival: Arrival,
    val departure: Departure,
    val scheduleRelationship: String,
    val stopId: String,
    val stopSequence: Int
)

// Data(Class):
data class Trip(
    // Fields:
    val routeId: String,
    val scheduleRelationship: String,
    val startDate: String,
    val startTime: String,
    val tripId: String,
    var convertedData: Route = Route("", "", "", "", "")
)

// Data(Routes):
data class Route(
    // Fields:
    val routeId: String,
    val agencyId: String,
    val routeShortName: String,
    val routeLongName: String,
    val routeType: String,
    val time: Int = 0
)

// Data(Stops):
data class Stop(
    // Fields:
    val stopId: String,
    val stopName: String,
    val stopLat: String,
    val stopLon: String,
    var routes: ArrayList<Route> = ArrayList()
)

// Data(Class):
data class TripUpdate(
    // Fields:
    val stopTimeUpdate: List<StopTimeUpdate>,
    val trip: Trip
)