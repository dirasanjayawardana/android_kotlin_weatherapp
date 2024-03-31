package com.dira.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MainActivity : AppCompatActivity() {
    private var latitude: TextView? = null
    private var longitude: TextView? = null
    private var altitude: TextView? = null
    private var akurasi: TextView? = null
    private var btnFind: Button? = null
    private var locationProviderClient: FusedLocationProviderClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        latitude = findViewById(R.id.latitude)
        longitude = findViewById(R.id.longitude)
        altitude = findViewById(R.id.altitude)
        akurasi = findViewById(R.id.akurasi)
        btnFind = findViewById(R.id.btn_find)
        locationProviderClient = LocationServices.getFusedLocationProviderClient(this@MainActivity)
        btnFind?.setOnClickListener(View.OnClickListener { _: View? -> location })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(
                    applicationContext,
                    "Izin lokasi tidak di aktifkan!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                location
            }
        }
    }

    @get:SuppressLint("SetTextI18n") // menonaktifkan lint warning yang terkait dengan setText
    private val location: Unit
        private get() {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // get Permission
                requestPermissions(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ), 10
                )
            } else {
                // get Location
                locationProviderClient!!.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        latitude!!.text = location.latitude.toString()
                        longitude!!.text = location.longitude.toString()
                        altitude!!.text = location.altitude.toString()
                        akurasi!!.text = location.accuracy.toString() + "%"
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Lokasi tidak aktif!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }.addOnFailureListener { e ->
                    Toast.makeText(
                        applicationContext,
                        e.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
}