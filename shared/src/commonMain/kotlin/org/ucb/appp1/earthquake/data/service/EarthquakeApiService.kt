package org.ucb.appp1.earthquake.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.ucb.appp1.earthquake.data.datasource.EarthquakeRemoteDataSource
import org.ucb.appp1.earthquake.data.dto.EarthquakeResponseDto

class EarthquakeApiService : EarthquakeRemoteDataSource {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    override suspend fun getEarthquakes(
        minMagnitude: Double,
        limit: Int
    ): EarthquakeResponseDto {
        val url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&minmagnitude=$minMagnitude&limit=$limit"
        val response = client.get(url)
        return response.body<EarthquakeResponseDto>()
    }
}
