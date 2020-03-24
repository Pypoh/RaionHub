package com.example.raionhub.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.raionhub.R
import com.example.raionhub.ui.main.scan.ScanActivity
import com.example.raionhub.utils.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_room,
                R.id.navigation_scan,
                R.id.navigation_memberlist,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Setup Scan onClick listener
        nav_button_scan.setOnClickListener {
            intentToCamera()
        }

    }

    private fun intentToCamera() {
        val intent = Intent(this@MainActivity, ScanActivity::class.java)
        startActivityForResult(intent, Constants.INTENT_CAMERA_SCAN_REQUEST)
    }
}
