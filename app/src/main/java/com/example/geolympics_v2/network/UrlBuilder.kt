package com.example.geolympics_v2.network

class UrlBuilder {
    companion object {
        fun getLatitude(latitudeMarker: String): String {
            return "https://image.tmdb.org/t/p/w200${latitudeMarker}"
        }

        fun getLongitude(longitudeMarker: String): String {
            return "https://image.tmdb.org/t/p/w500${longitudeMarker}"
        }
    }
}