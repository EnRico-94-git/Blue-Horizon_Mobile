package com.example.ocenapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.ocenapp.databinding.ActivityMainBinding


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.textView.text = "Blue Horizon"
    }
}