@file:Suppress("unused")

package com.transit.information.model

import com.google.gson.annotations.SerializedName

// Data(Class):
data class Transit(
    // Fields:
    @SerializedName("Entity")
    val entities: List<Entity>,
    @SerializedName("Header")
    val header: Header
)

// Data(Class):
data class Arrival(
    // Fields:
    @SerializedName("Delay")
    val delay: Int,
    @SerializedName("Time")
    val time: Int
)

// Data(Class):
data class Departure(
    // Fields:
    @SerializedName("Delay")
    val delay: Int,
    @SerializedName("Time")
    val time: Int
)

// Data(Class):
data class Entity(
    // Fields:
    @SerializedName("Id")
    val id: String,
    @SerializedName("IsDeleted")
    val isDeleted: Boolean,
    @SerializedName("TripUpdate")
    val tripUpdate: TripUpdate
)

// Data(Class):
data class Header(
    // Fields:
    @SerializedName("GtfsRealtimeVersion")
    val gtfsRealtimeVersion: String,
    @SerializedName("Incrementality")
    val incrementality: String,
    @SerializedName("Timestamp")
    val timestamp: Int
)

// Data(Class):
data class StopTimeUpdate(
    // Fields:
    @SerializedName("Arrival")
    val arrival: Arrival,
    @SerializedName("Departure")
    val departure: Departure,
    @SerializedName("ScheduleRelationship")
    val scheduleRelationship: String,
    @SerializedName("StopId")
    val stopId: String,
    @SerializedName("StopSequence")
    val stopSequence: Int
)

// Data(Class):
data class Trip(
    // Fields:
    @SerializedName("RouteId")
    val routeId: String,
    @SerializedName("ScheduleRelationship")
    val scheduleRelationship: String,
    @SerializedName("StartDate")
    val startDate: String,
    @SerializedName("StartTime")
    val startTime: String,
    @SerializedName("TripId")
    val tripId: String
)

// Data(Class):
data class TripUpdate(
    // Fields:
    @SerializedName("StopTimeUpdate")
    val stopTimeUpdate: List<StopTimeUpdate>,
    @SerializedName("Trip")
    val trip: Trip
)