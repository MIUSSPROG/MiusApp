package com.example.miusapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import com.example.miusapp.databinding.ActivityMainBinding
import com.example.miusapp.databinding.ActivityRegBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
    }

    fun btnSignUp_Click(view: View) {

        val email = binding.etEmailReg.text.toString().trim()
        val name = binding.etFioReg.text.toString().trim()
        val pass = binding.etPassReg.text.toString().trim()
        val confirmPass = binding.etPassConfirmReg.text.toString().trim()
        if (email.isNotEmpty() && name.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
            if (pass == confirmPass) {
                auth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener {

                    val user: MutableMap<String, Any> = HashMap()
                    user["email"] = email
                    user["name"] = name

                    db.collection("Users").add(user)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Вы успешно зарегистрировались!", Toast.LENGTH_SHORT).show()
                            Handler(Looper.getMainLooper()).postDelayed({
                                startActivity(Intent(this, MainActivity::class.java))
                            }, 600)
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            else{
                Toast.makeText(this, "Пароли не совпадают!", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show()
        }

    }

    fun btnGoToLogin_Click(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

}