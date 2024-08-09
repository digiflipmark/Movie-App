package com.finder.movieapp.di

import com.finder.movieapp.core_feature.data.remote.MoviesApi
import com.finder.movieapp.core_feature.data.repository.MoviesRepositoryImp
import com.finder.movieapp.core_feature.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(moviesApi: MoviesApi): MoviesRepository {
        return MoviesRepositoryImp(moviesApi = moviesApi)
    }

}