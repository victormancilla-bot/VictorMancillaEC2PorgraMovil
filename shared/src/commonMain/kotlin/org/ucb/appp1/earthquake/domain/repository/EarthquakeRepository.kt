package org.ucb.appp1.earthquake.domain.repository

import org.ucb.appp1.earthquake.domain.model.EarthquakeModel

interface EarthquakeRepository {
    suspend fun getEarthquakes(minMagnitude: Double = 5.0, limit: Int = 3): Result<List<EarthquakeModel>>
}
