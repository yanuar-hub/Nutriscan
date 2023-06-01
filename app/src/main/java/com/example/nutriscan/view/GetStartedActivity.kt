package com.example.nutriscan.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nutriscan.databinding.ActivityGetStartedBinding


class GetStartedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGetStartedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}