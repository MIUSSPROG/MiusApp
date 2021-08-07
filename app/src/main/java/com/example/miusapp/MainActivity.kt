package com.example.miusapp

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.miusapp.Utils.prefs
import com.example.miusapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(prefs.myUUId.isNotEmpty()){
            startActivity(Intent(this, NavigationActivity::class.java))
        }

        auth = FirebaseAuth.getInstance()
    }

    fun btnSign_Click(view: View) {
        val email = binding.etEmailLogin.text.toString().trim()
        val pass = binding.etPassLogin.text.toString().trim()

        if (email.isNotEmpty() && pass.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener {
                    prefs.myUUId = auth.currentUser?.uid.toString()
                    Toast.makeText(this, "Успешный вход!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, NavigationActivity::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Произошла ошибка!", Toast.LENGTH_SHORT).show()
                }
        }
        else{
            Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show()
        }
    }

    fun btnGoToReg(view: View) {
        startActivity(Intent(this, RegActivity::class.java))
    }
}