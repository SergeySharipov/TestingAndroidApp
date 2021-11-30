package ca.sharipov.testingandroidapp.data.remote

import ca.sharipov.testingandroidapp.BuildConfig
import ca.sharipov.testingandroidapp.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.PIXABAY_API_KEY
    ): Response<ImageResponse>
}