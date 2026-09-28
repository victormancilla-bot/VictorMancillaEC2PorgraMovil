package org.ucb.appp1.earthquake.data.datasource

import org.ucb.appp1.earthquake.data.dto.EarthquakeResponseDto

interface EarthquakeRemoteDataSource {
    suspend fun getEarthquakes(minMagnitude: Double = 5.0, limit: Int = 3): EarthquakeResponseDto
}
