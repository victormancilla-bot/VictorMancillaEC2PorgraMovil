package org.ucb.appp1.earthquake.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EarthquakeResponseDto(
    @SerialName("features")
    val features: List<EarthquakeFeatureDto>? = null
)

@Serializable
data class EarthquakeFeatureDto(
    @SerialName("properties")
    val properties: EarthquakePropertiesDto? = null,
    @SerialName("geometry")
    val geometry: EarthquakeGeometryDto? = null
)

@Serializable
data class EarthquakePropertiesDto(
    @SerialName("place")
    val place: String? = null,
    @SerialName("mag")
    val mag: Double? = null,
    @SerialName("time")
    val time: Long? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class EarthquakeGeometryDto(
    @SerialName("coordinates")
    val coordinates: List<Double>? = null
)
