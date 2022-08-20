package com.david.sciflare.main.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.david.sciflare.R
import com.david.sciflare.databinding.ActivityMapBinding
import com.david.sciflare.main.ui.example_list.adapter.ExampleAdapter
import com.david.sciflare.main.utility.selfCheckPermission
import com.david.support.base_class.ActionBarActivity
import com.david.support.inline.orElse
import com.david.support.utility.debugging.Log
import com.example.easywaylocation.EasyWayLocation
import com.example.easywaylocation.EasyWayLocation.LOCATION_SETTING_REQUEST_CODE
import com.example.easywaylocation.Listener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.toast

@AndroidEntryPoint
class MapActivity() : ActionBarActivity<ActivityMapBinding, MapViewModel>(
    R.layout.activity_map,
    MapViewModel::class.java
), ExampleAdapter.ItemListener {

    private var googleMap: GoogleMap? = null
    private lateinit var easyWayLocation: EasyWayLocation
    private var alertDialog: AlertDialog? = null
    private lateinit var exampleAdapter: ExampleAdapter

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        initPermission()
        initGps()
        initData()
        initMap()
        initListener()
        initPreview()
    }

    private fun initPermission() {
        /*
        * Check location permission allowed
        */
        if (selfCheckPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            Log.i("initPermission", "have permission")

        } else if (ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            toast("Location permission required")
        } else {
            /*
            * request when location permission denied
            */
            ActivityCompat.requestPermissions(
                this@MapActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 101
            );
        }
    }

    private fun initGps() {
        /*
        *Listen for location changes every 1 minutes
        */
        easyWayLocation = EasyWayLocation(this, false, false, object : Listener {
            override fun locationOn() {
                Log.e("MainScreen", "locationOn")
            }

            override fun currentLocation(location: Location?) {
                Log.e(
                    "MainScreen",
                    "currentLocation: lat.${location?.latitude} lng.${location?.longitude}"
                )
                location?.let {
                    googleMap?.let { it1 -> markMyLocation(it1, LatLng(it.latitude, it.longitude)) }
                        .orElse {
                            "Map not initialized".apply {
                                Log.e("initGps", this)
                                toast(this)
                            }
                        }
                }.orElse {
                    "location not be null".apply {
                        Log.e("initGps", this)
                        toast(this)
                    }
                }
            }

            override fun locationCancelled() {
                Log.e("MainScreen", "locationCancelled")
            }
        })
    }

    private fun initData() {
        //todo:
    }

    @SuppressLint("MissingPermission")
    private fun initMap() {
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment?
        mapFragment?.getMapAsync { googleMap ->
            /*Add marker into map*/
            this@MapActivity.googleMap = googleMap
        }
    }

    private fun initListener() {
    }

    private fun initPreview() {
        setTitle("Map")
        setNavigateUpEnabled(true)
    }

    /*
    * uses maps.goolgemap parameter1
    * uses gmaps.latlng parameter2
    *
    * marks user current location by latlng
    * */
    @SuppressLint("MissingPermission")
    private fun markMyLocation(googleMap: GoogleMap, latLng: LatLng) {
        googleMap.addMarker(
            MarkerOptions().position(latLng)
                .icon(
                    bitmapDescriptorFromVector(
                        this@MapActivity,
                        R.drawable.ic_baseline_location_on_24
                    )
                )
        ).showInfoWindow()
        googleMap.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                this@MapActivity,
                R.raw.map_style_json
            )
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom((latLng), 11.0F))
        googleMap.isMyLocationEnabled = true //showing current location
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap =
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            101 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (selfCheckPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
                        easyWayLocation.startLocation()
                    }
                } else {
                    toast("Permission required")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        easyWayLocation?.startLocation() //enable  location listening
    }

    override fun onPause() {
        super.onPause()
        easyWayLocation?.endUpdates() //disable location listening
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            LOCATION_SETTING_REQUEST_CODE -> {
                easyWayLocation.onActivityResult(resultCode)
            }
        }
    }

    companion object {
        fun intent(activity: Activity): Intent {
            return Intent(activity, MapActivity::class.java)
        }
    }
}
