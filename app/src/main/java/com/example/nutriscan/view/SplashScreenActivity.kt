package com.example.nutriscan.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.example.nutriscan.databinding.ActivitySplashScreenBinding
import com.example.nutriscan.utils.UserPreference
import com.example.nutriscan.utils.dataStore
import com.example.nutriscan.viewmodel.SplashScreenViewModel
import com.example.nutriscan.viewmodel.ViewModelFactory
import java.util.Timer
import kotlin.concurrent.schedule

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySplashScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val pref = UserPreference.getInstance(dataStore)
        val splashViewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[SplashScreenViewModel::class.java]

        splashViewModel.getToken()
            .observe(this) { token ->
                if (token == "No User Token") Timer().schedule(2000) {
                    startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                    finish()
                } else Timer().schedule(2000) {
                    startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                    finish()
                }
            }
    }

}