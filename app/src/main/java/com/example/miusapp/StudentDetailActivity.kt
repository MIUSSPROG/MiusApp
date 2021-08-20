package com.example.miusapp

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.miusapp.Adapter.DiagnosticMapAdapter
import com.example.miusapp.Model.Group
import com.example.miusapp.Utils.prefs
import com.example.miusapp.databinding.ActivityDetailNavigationBinding
import com.example.miusapp.databinding.ActivityStudentDetailBinding
import com.google.firebase.firestore.FirebaseFirestore

class StudentDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var groupId: String
    private lateinit var studentId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStudentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        val studentName = intent.extras?.get("studentName").toString()
        groupId = intent.extras?.get("groupId").toString()
        studentId = intent.extras?.get("studentId").toString()

        binding.tvStudentCardName.text = studentName

        binding.btnBackStudentDetail.setOnClickListener {
            finish()
        }

        // CompoundView

        binding.studentCardTheor1.setData(groupId,studentId,resources.getString(R.string.theor1), "theorTrain1First", "theorTrain1Second")
        binding.studentCardTheor2.setData(groupId,studentId,resources.getString(R.string.theor2), "theorTrain2First", "theorTrain2Second")

        binding.studentCardPractic1.setData(groupId,studentId,resources.getString(R.string.practic1), "practicTrain1First", "practicTrain1Second")
        binding.studentCardPractic2.setData(groupId,studentId,resources.getString(R.string.practic2), "practicTrain2First", "practicTrain2Second")
        binding.studentCardPractic3.setData(groupId,studentId,resources.getString(R.string.practic3), "practicTrain3First", "practicTrain3Second")

        binding.studentCardGeneral1.setData(groupId,studentId,resources.getString(R.string.general1), "generalTrain1First", "generalTrain1Second")
        binding.studentCardGeneral2.setData(groupId,studentId,resources.getString(R.string.general2), "generalTrain2First", "generalTrain2Second")
        binding.studentCardGeneral3.setData(groupId,studentId,resources.getString(R.string.general3), "generalTrain3First", "generalTrain3Second")
        binding.studentCardGeneral4.setData(groupId,studentId,resources.getString(R.string.general4), "generalTrain4First", "generalTrain4Second")
        binding.studentCardGeneral5.setData(groupId,studentId,resources.getString(R.string.general5), "generalTrain5First", "generalTrain5Second")
        binding.studentCardGeneral6.setData(groupId,studentId,resources.getString(R.string.general6), "generalTrain6First", "generalTrain6Second")
        binding.studentCardGeneral7.setData(groupId,studentId,resources.getString(R.string.general7), "generalTrain7First", "generalTrain7Second")
        binding.studentCardGeneral8.setData(groupId,studentId,resources.getString(R.string.general8), "generalTrain8First", "generalTrain8Second")
        binding.studentCardGeneral9.setData(groupId,studentId,resources.getString(R.string.general9), "generalTrain9First", "generalTrain9Second")
        binding.studentCardGeneral10.setData(groupId,studentId,resources.getString(R.string.general10), "generalTrain10First", "generalTrain10Second")
        binding.studentCardGeneral11.setData(groupId,studentId,resources.getString(R.string.general11), "generalTrain11First", "generalTrain11Second")



    }

}