package com.finder.movieapp.core_feature.data.remote

import com.finder.movieapp.core_feature.data.remote.dto.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apikey: String = "ac42f35ab92085ed961dd5aa4bc8dce5",
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): Response<MovieResponse>


    @GET("movie/now_playing")
    suspend fun getPopularMovies(
        @Query("api_key") apikey: String = "ac42f35ab92085ed961dd5aa4bc8dce5",
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): Response<MovieResponse>


    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apikey: String = "ac42f35ab92085ed961dd5aa4bc8dce5",
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): Response<MovieResponse>


    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apikey: String = "ac42f35ab92085ed961dd5aa4bc8dce5",
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): Response<MovieResponse>


    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("api_key") apikey: String = "ac42f35ab92085ed961dd5aa4bc8dce5",
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): Response<MovieResponse>


}