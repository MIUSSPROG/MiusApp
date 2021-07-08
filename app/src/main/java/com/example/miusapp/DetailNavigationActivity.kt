package com.example.miusapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.miusapp.databinding.ActivityDetailNavigationBinding
import com.example.miusapp.databinding.ActivityMainBinding

class DetailNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val desc = intent.extras?.get("desc").toString()
        binding.tvNavDetailTitle.text = desc
    }
}