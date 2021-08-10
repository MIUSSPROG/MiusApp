package com.example.miusapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.miusapp.Fragments.DiagnosticMapFragment
import com.example.miusapp.Fragments.UserHomeFragment
import com.example.miusapp.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(UserHomeFragment())
        binding.navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.nav_home -> {
                    loadFragment(UserHomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.nav_analytics -> {
                    loadFragment(DiagnosticMapFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                else -> false
            }
        }
    }

    override fun onBackPressed() {

    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.container.id, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}