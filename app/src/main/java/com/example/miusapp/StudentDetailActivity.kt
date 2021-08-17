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

        binding.studentCardTheor1.setData(groupId,studentId,"Теоретические знания, предусмотренные программой", "theorTrain1First", "theorTrain1Second")
        binding.studentCardTheor2.setData(groupId,studentId,"Владение специальной терминологией", "theorTrain2First", "theorTrain2Second")

        binding.studentCardPractic1.setData(groupId,studentId,"Практические умения и навыки, предусмотренные программой", "practicTrain1First", "practicTrain1Second")
        binding.studentCardPractic2.setData(groupId,studentId,"Владение специальным оборудованием и оснащением", "practicTrain2First", "practicTrain2Second")
        binding.studentCardPractic3.setData(groupId,studentId,"Творческие навыки", "practicTrain3First", "practicTrain3Second")

        binding.studentCardGeneral1.setData(groupId,studentId,"Подбирать и анализировать специальную литературу", "generalTrain1First", "generalTrain1Second")
        binding.studentCardGeneral2.setData(groupId,studentId,"Пользоваться источниками информации из коммуникационных сетей", "generalTrain2First", "generalTrain2Second")
        binding.studentCardGeneral3.setData(groupId,studentId,"Пользоваться программными продуктами и WEB-ресурсами", "generalTrain3First", "generalTrain3Second")
        binding.studentCardGeneral4.setData(groupId,studentId,"Осуществлять  учебно-исследовательскую работу ", "generalTrain4First", "generalTrain4Second")
        binding.studentCardGeneral5.setData(groupId,studentId,"Слушать и слышать педагога, принимать во внимание мнение других людей", "generalTrain5First", "generalTrain5Second")
        binding.studentCardGeneral6.setData(groupId,studentId,"Выступать перед аудиторией", "generalTrain6First", "generalTrain6Second")
        binding.studentCardGeneral7.setData(groupId,studentId,"Участвовать в дискуссии, защищать свою точку зрения", "generalTrain7First", "generalTrain7Second")
        binding.studentCardGeneral8.setData(groupId,studentId,"Организовывать свое рабочее (учебное) место", "generalTrain8First", "generalTrain8Second")
        binding.studentCardGeneral9.setData(groupId,studentId,"Соблюдения в процессе деятельности правила ТБ", "generalTrain9First", "generalTrain9Second")
        binding.studentCardGeneral10.setData(groupId,studentId,"Аккуратно, ответственно выполнять работу", "generalTrain10First", "generalTrain10Second")
        binding.studentCardGeneral11.setData(groupId,studentId,"Умение планировать ", "generalTrain11First", "generalTrain11Second")



    }

}