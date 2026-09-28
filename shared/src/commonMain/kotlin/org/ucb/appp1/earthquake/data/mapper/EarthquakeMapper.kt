package org.ucb.appp1.earthquake.data.mapper

import org.ucb.appp1.earthquake.data.dto.EarthquakeFeatureDto
import org.ucb.appp1.earthquake.domain.model.EarthquakeModel

fun EarthquakeFeatureDto.toDomain(): EarthquakeModel {
    val props = properties
    val coords = geometry?.coordinates ?: emptyList()
    return EarthquakeModel(
        place = props?.place ?: "",
        mag = props?.mag ?: 0.0,
        time = props?.time ?: 0L,
        url = props?.url ?: "",
        longitude = coords.getOrNull(0) ?: 0.0,
        latitude = coords.getOrNull(1) ?: 0.0,
        depth = coords.getOrNull(2) ?: 0.0
    )
}
