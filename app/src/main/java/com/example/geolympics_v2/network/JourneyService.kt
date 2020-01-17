package com.example.geolympics_v2.network

import com.example.geolympics_v2.network.response.JourneyResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header


interface JourneyService {

    // https://api.navitia.io/v1/coverage/fr-nw/journeys?from=2.3749036%3B48.8467927&to=2.2922926%3B48.8583736&
    // %3B = ;
    @GET("coverage/fr-nw/journeys?from=2.321609%3B48.880315&to=2.420295%3B48.851850&")
    fun getJourney(
        @Header("Authorization") authorization:String): Call<JourneyResult>
}