package com.example.raionhub.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.raionhub.R
import com.example.raionhub.repository.datasource.remote.auth.other.AuthRepoImpl
import com.example.raionhub.ui.auth.AuthActivity
import com.example.raionhub.ui.auth.domain.AuthImpl
import com.example.raionhub.ui.main.MainActivity
import com.example.raionhub.utils.Constants
import com.example.raionhub.utils.viewobject.Resource

class SplashScreenActivity : AppCompatActivity() {

    // View Model
    private val splashScreenViewModel: SplashScreenViewModel by lazy {
        ViewModelProvider(
            this,
            SplashVMFactory(AuthImpl(AuthRepoImpl()))
        ).get(SplashScreenViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash_screen)

        // Observe LiveData
        splashScreenViewModel.authInstance.observe(this, Observer {
            when (it) {
                is Resource.Success -> if (it.data.currentUser != null) {
                    navigateToMainActivity()
                } else {
                    navigateToAuthActivity()
                }

                is Resource.Failure -> {
                    Thread.sleep(Constants.SPLASH_SCREEN_DELAY)
                    navigateToMainActivity()
                }
            }
        })
    }

    private fun navigateToAuthActivity() {
        finish()
        startActivity(Intent(this, AuthActivity::class.java))
    }

    private fun navigateToMainActivity() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }
}
