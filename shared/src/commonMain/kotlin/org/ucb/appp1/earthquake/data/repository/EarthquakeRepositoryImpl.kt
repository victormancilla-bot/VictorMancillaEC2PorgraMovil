package org.ucb.appp1.earthquake.data.repository

import org.ucb.appp1.earthquake.data.datasource.EarthquakeRemoteDataSource
import org.ucb.appp1.earthquake.data.mapper.toDomain
import org.ucb.appp1.earthquake.domain.model.EarthquakeModel
import org.ucb.appp1.earthquake.domain.repository.EarthquakeRepository

class EarthquakeRepositoryImpl(
    private val dataSource: EarthquakeRemoteDataSource
) : EarthquakeRepository {
    override suspend fun getEarthquakes(
        minMagnitude: Double,
        limit: Int
    ): Result<List<EarthquakeModel>> {
        return runCatching {
            val response = dataSource.getEarthquakes(minMagnitude, limit)
            response.features?.map { it.toDomain() } ?: emptyList()
        }
    }
}
