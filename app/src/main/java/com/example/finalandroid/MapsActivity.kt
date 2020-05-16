package com.example.finalandroid

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
        mMap.setOnMarkerClickListener(this)
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(10F), 2000, null)

        // Add a marker in Sydney and move the camera
        val  hgz = LatLng(19.307486, -99.131113)
        mMap.addMarker(MarkerOptions().position(hgz).title("Hospital General de la Zona 32"))

        val medicaSur = LatLng(19.297103, -99.160999)
        mMap.addMarker(MarkerOptions().position(medicaSur).title("Medica Sur"))

        val iner = LatLng(19.292258, -99.159072)
        mMap.addMarker(MarkerOptions().position(iner).title("Instituto Nacional de Enfermedades Respiratorias"))

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(Tec))
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                medicaSur,
                12f
            )
        )


    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        val intent = Intent(this,HospitalActivity::class.java)
        startActivity(intent)
        return true
    }
}
