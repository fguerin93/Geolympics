package com.example.geolympics_v2

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.descScreen
import kotlinx.android.synthetic.main.activity_main.titleScreen
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Fonts
        val typefaceBold = Typeface.createFromAsset(assets, "Eina01-Bold.ttf")
        val typefaceRegular = Typeface.createFromAsset(assets, "Eina01-Regular.ttf")

        // Set the typeface
        titleScreen.typeface = typefaceBold

        // Set the typeface
        titleTransports.typeface = typefaceBold
        descTransports.typeface = typefaceRegular

        buttonTransports.typeface = typefaceBold

        titleFast.typeface = typefaceRegular
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val parisPlace = LatLng(48.856614, 2.352222)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(parisPlace, 10.0f))

        // Add static marker of place
        val swimmingPlace = LatLng(48.910513, 2.384068)
        mMap.addMarker(MarkerOptions().position(swimmingPlace).title("Swimming - Centre Aquatique Aubervilliers"))

        val athleticsPlace = LatLng(48.924459, 2.360164)
        mMap.addMarker(MarkerOptions().position(athleticsPlace).title("Athletics - Stade de France"))


        val tennisPlace = LatLng(48.847344, 2.250917)
        mMap.addMarker(MarkerOptions().position(tennisPlace).title("Tennis - Rolland Garros"))


    }
}
