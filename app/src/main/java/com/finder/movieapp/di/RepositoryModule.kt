package com.finder.movieapp.di

import com.finder.movieapp.core_feature.data.local.MovieDao
import com.finder.movieapp.core_feature.data.remote.ImageApi
import com.finder.movieapp.core_feature.data.remote.MoviesApi
import com.finder.movieapp.core_feature.data.repository.ImageRepositoryImp
import com.finder.movieapp.core_feature.data.repository.MovieDbRepoImp
import com.finder.movieapp.core_feature.data.repository.MoviesRepositoryImp
import com.finder.movieapp.core_feature.domain.repository.ImageRepository
import com.finder.movieapp.core_feature.domain.repository.MovieDbRepository
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

    @Provides
    @Singleton
    fun provideImageRepository(imageApi: ImageApi): ImageRepository {
        return ImageRepositoryImp(imageApi)
    }

    @Provides
    @Singleton
    fun provideMovieDbRepository(movieDao: MovieDao): MovieDbRepository {
        return MovieDbRepoImp(movieDao)
    }
}