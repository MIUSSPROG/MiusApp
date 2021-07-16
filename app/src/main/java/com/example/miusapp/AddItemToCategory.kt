package com.example.miusapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.miusapp.databinding.ActivityAddItemToCategoryBinding
import com.example.miusapp.databinding.ActivityDetailNavigationBinding

class AddItemToCategory : AppCompatActivity() {

    private lateinit var binding: ActivityAddItemToCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddItemToCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var title = intent.extras?.getString("itemTitle")
        binding.tvAddItemTitle.text = title
    }
}