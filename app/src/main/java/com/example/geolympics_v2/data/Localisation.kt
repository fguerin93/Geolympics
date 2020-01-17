package com.example.geolympics_v2.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize data class Localisation(var id : String, var latitude : Double, var longitude : Double) :
    Parcelable {
}