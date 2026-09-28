package org.ucb.appp1.earthquake.domain.usecase

import org.ucb.appp1.earthquake.domain.model.EarthquakeModel
import org.ucb.appp1.earthquake.domain.repository.EarthquakeRepository

class GetEarthquakesUseCase(
    private val repository: EarthquakeRepository
) {
    suspend fun invoke(minMagnitude: Double = 5.0, limit: Int = 3): Result<List<EarthquakeModel>> {
        return repository.getEarthquakes(minMagnitude, limit)
    }
}
