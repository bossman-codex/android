package com.example.babybuy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.babybuy.databinding.ActivitySplashScreenBinding


class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivLogo.alpha = 0f
        binding.ivLogo.animate().setDuration (3500).rotationYBy(360F).alpha(1f).withEndAction {
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