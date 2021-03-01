package com.ylt.toursdeliberations.service

import com.ylt.toursdeliberations.model.Deliberations
import com.ylt.toursdeliberations.model.DeliberationsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DeliberationsService {

    @GET("api/records/1.0/search/?dataset=deliberations-tours-metropole-val-de-loire&q=&sort=delib_date&rows=100&facet=delib_id&facet=delib_date&facet=delib_objet&facet=type_seance&facet=themes")
    suspend fun getDeliberations(): DeliberationsResponse

    companion object {
        private const val BASE_URL = "https://data.tours-metropole.fr/"

        fun create(): DeliberationsService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC
            //else HttpLoggingInterceptor.Level.NONE

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(DeliberationsService::class.java)
        }
    }
}