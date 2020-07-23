package jetpack.skill.userlocation_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import jetpack.skill.userlocation_android.utils.LocationUtility

class MainActivity : AppCompatActivity() {

    private lateinit var locationUtility: LocationUtility

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationUtility = LocationUtility(this)
        locationUtility.startLocationClient()

        lifecycle.addObserver(locationUtility)

        locationUtility.currentLocation.observe(this, Observer {
            println("Location Received ${it.first} ${it.second}")
        })
    }


    override fun onDestroy() {
        lifecycle.removeObserver(locationUtility);
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        locationUtility.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
    ) {
        locationUtility.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}