package com.example.exercice2

import android.Manifest
import android.app.AlertDialog
import android.content.Context.LOCATION_SERVICE
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    val requestCodePermissions: Int = 2

    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(
            context,
            android.R.string.yes, Toast.LENGTH_SHORT
        ).show()
        requestPermissions()
    }
    val negativeButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(
            context,
            android.R.string.no, Toast.LENGTH_SHORT
        ).show()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.location_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = view.findViewById<TextView>(R.id.location)
        fusedLocationClient = context?.let { LocationServices.getFusedLocationProviderClient(it) }!!

        if (context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            // la permission n’est pas accordée
            Log.d("Error", "pas accordé")
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                Log.d("Error", "Vous avez refusé")

                val builder = AlertDialog.Builder(context)
                builder.setTitle("Permission obligatoire")
                builder.setMessage("Nous avons besoin de la permission afin de déterminer votre position GPS ")
                builder.setPositiveButton(
                    "Ok",
                    DialogInterface.OnClickListener(function = positiveButtonClick)
                )
                builder.setNegativeButton(
                    android.R.string.no,
                    DialogInterface.OnClickListener(function = negativeButtonClick)
                )
                builder.show()
            } else {
                //Demander la permission
                requestPermissions()
            }
        } else {
            Log.d("Error", "accordé")
            displayLocation(result)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        val result = view?.findViewById<TextView>(R.id.location)
        if (result != null) {
            when (requestCode) {
                requestCodePermissions -> {
                    if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                        //La permission est accordée, on adapte la vue en conséquence
                            displayLocation(result)

                    } else {
                            result.text = "Error"
                    }
                }
                else -> {
                    // Le code ne concerne pas la permission, on l'ignore
                }
            }
        }
    }

    private fun requestPermissions() {

        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            requestCodePermissions
        )
    }

    private fun displayLocation(result: TextView){
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // Got last known location. In some rare situations this can be null.
                val lat = location?.latitude.toString()
                val lon = location?.longitude.toString()

                result.text = "Longitude: ${lon} \n Latitude: ${lat}"
            }
    }
}