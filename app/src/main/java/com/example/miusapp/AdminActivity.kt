package com.example.miusapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.miusapp.Fragments.AdminAnalyticsFragment
import com.example.miusapp.Fragments.DiagnosticMapFragment
import com.example.miusapp.Fragments.UserAccountFragment
import com.example.miusapp.Fragments.UserHomeFragment
import com.example.miusapp.databinding.ActivityAdminBinding
import com.example.miusapp.databinding.ActivityNavigationBinding
import com.google.firebase.firestore.FirebaseFirestore

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(AdminAnalyticsFragment())
        binding.navigationViewAdmin.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.nav_admin_analytics -> {
                    loadFragment(AdminAnalyticsFragment())
                    return@setOnNavigationItemSelectedListener true
                }

//                R.id.nav_admin_chat -> {
//                    loadFragment(DiagnosticMapFragment())
//                    return@setOnNavigationItemSelectedListener true
//                }

                else -> false
            }
        }
    }

    override fun onBackPressed() {

    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.containerAdmin.id, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}