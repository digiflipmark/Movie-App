package com.finder.movieapp.core_feature.data.remote

import com.finder.movieapp.core_feature.data.remote.dto.MovieResponse
import com.finder.movieapp.core_feature.data.remote.dto.cast.CastDto
import com.finder.movieapp.core_feature.data.remote.dto.details.MovieDetailsResponse
import com.finder.movieapp.core_feature.data.remote.dto.review.ReviewDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apikey: String = "ac42f35ab92085ed961dd5aa4bc8dce5",
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): MovieResponse


    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apikey: String = "ac42f35ab92085ed961dd5aa4bc8dce5",
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): MovieResponse


    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apikey: String = "ac42f35ab92085ed961dd5aa4bc8dce5",
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): MovieResponse


    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apikey: String = "ac42f35ab92085ed961dd5aa4bc8dce5",
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): MovieResponse


    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("api_key") apikey: String = "ac42f35ab92085ed961dd5aa4bc8dce5",
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): MovieResponse


    //movieDetails
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "ac42f35ab92085ed961dd5aa4bc8dce5",
        @Query("language") language: String = "en-US",
    ): MovieDetailsResponse

    //Cast
    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "ac42f35ab92085ed961dd5aa4bc8dce5",
        @Query("language") language: String = "en-US",
    ): CastDto

    //Review
    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "ac42f35ab92085ed961dd5aa4bc8dce5",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
    ): ReviewDto
}