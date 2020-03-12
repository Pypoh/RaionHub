package com.example.raionhub.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.animation.Animation
import com.example.raionhub.R
import com.example.raionhub.ui.auth.login.LoginActivity
import com.example.raionhub.utils.Constants

class SplashScreenActivity : AppCompatActivity() {

        private lateinit var mDelayHandler: Handler

        private val mRunnable: Runnable = Runnable {
            if(!isFinishing){
                navigateToLoginActivitiy()
            }
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.activity_splash_screen)

            //animation = AnimationUtils.loadAnimation(this@SplashScreenActivity, R.anim.splash_anim)
            //imageSplash_splash.startAnimation(animation)

            //Initialize the Handler
            mDelayHandler = Handler()

            //Navigate with delay
            mDelayHandler.postDelayed(mRunnable, Constants.SPLASH_SCREEN_DELAY)
        }

        private fun navigateToLoginActivitiy() {
            val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }


        override fun onPause() {
            super.onPause()
            finish()
        }

        override fun onResume() {
            super.onResume()
        }

        public override fun onDestroy() {
            super.onDestroy()
        }
}