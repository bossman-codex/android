package com.example.babybuy

import android.content.Intent
import kotlinx.android.synthetic.main.activity_splash_screen.*
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        iv_logo.alpha = 0f
        iv_logo.animate().setDuration (3500).rotationYBy(360F).alpha(1f).withEndAction {
            val i = Intent( this, WelcomeScreen::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
//
//        imageView4.SetOnClickListener {
//
//        }
//        image_splash.setOnClickListener {
//            iv_image.animate().apply{
//                duration : 1000
//                rotateYBy(360)
//            }.start()
//
//        }
    }
}