package org.ucb.appp1.earthquake.domain.model

data class EarthquakeModel(
    val place: String,
    val mag: Double,
    val time: Long,
    val url: String,
    val longitude: Double,
    val latitude: Double,
    val depth: Double
)
