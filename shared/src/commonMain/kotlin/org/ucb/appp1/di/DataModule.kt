package org.ucb.appp1.di

import org.koin.dsl.module
import org.ucb.appp1.earthquake.data.datasource.EarthquakeRemoteDataSource
import org.ucb.appp1.earthquake.data.repository.EarthquakeRepositoryImpl
import org.ucb.appp1.earthquake.data.service.EarthquakeApiService
import org.ucb.appp1.earthquake.domain.repository.EarthquakeRepository
import org.ucb.appp1.userinformation.data.datasource.GithubRemoteDataSource
import org.ucb.appp1.userinformation.data.repository.GithubRepositoryImpl
import org.ucb.appp1.userinformation.data.service.GitHubApiService
import org.ucb.appp1.userinformation.domain.repository.GithubRepository

val dataModule = module {
    single<GithubRemoteDataSource> { GitHubApiService() }
    single<GithubRepository>{GithubRepositoryImpl(get())}
    single<EarthquakeRemoteDataSource> { EarthquakeApiService() }
    single<EarthquakeRepository> { EarthquakeRepositoryImpl(get()) }
}
