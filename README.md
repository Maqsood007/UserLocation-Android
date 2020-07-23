# UserLocation-Android
Get user location with FusedLocationProviderClient


Steps to integrate:

#1: Add Permission:

    <uses-permission android:name="android.permission.INTERNET" /> 
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
    
#2: Required Dependencies:

    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
    implementation 'com.google.android.gms:play-services-location:17.0.0'
    
#3: Copy the LocationUtility.kt class into your project:

    LocationUtility.kt (Main class contain location access logic)
    
#4: Add Below code to your Activity

     private lateinit var locationUtility: LocationUtility
    
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
    
            /**
            * your other code and object initialisation
            */
    
            locationUtility = LocationUtility(this)
            locationUtility.startLocationClient()
    
    
            // This is to make LocationUtility.kt life cycle aware component
            lifecycle.addObserver(locationUtility)
    
            // Observe Locaiton updates
            locationUtility.currentLocation.observe(this, Observer {
                println("Location Received ${it.first} ${it.second}")
            })       
    
        }
        
        
#5: Add below code to handle different events 
        
        /**
         * remove locationUtility from listening the activity lifecycle.
         */
        override fun onDestroy() {
                lifecycle.removeObserver(locationUtility);
                super.onDestroy()
            }
            
         /**
          * navigate the objects to locationUtility from onActivityResult.
          */   
        
            override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                super.onActivityResult(requestCode, resultCode, data)
                locationUtility.onActivityResult(requestCode, resultCode, data)
            }
        
        /**
         * navigate the objects to locationUtility from onRequestPermissionsResult.
         */
            override fun onRequestPermissionsResult(
                requestCode: Int,
                permissions: Array<String>,
                grantResults: IntArray
            ) {
                locationUtility.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
            
            
            
            
https://github.com/Maqsood007/UserLocation-Android/blob/master/device-2020-07-24-024316.png
https://github.com/Maqsood007/UserLocation-Android/blob/master/device-2020-07-24-024437.png