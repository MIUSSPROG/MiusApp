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
    private lateinit var studentCardTrain: StudentCardTrain

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

        studentCardTrain = binding.studentCardTrain1
        studentCardTrain.setData(groupId,studentId,"Выступать перед аудиторией", "generalTrain2First", "generalTrain2Second")

        // UPDATES
        db.collection("Users").document(prefs.myUUId)
            .collection("Секции").document(resources.getString(R.string.diagnosticMap)).collection("группы")
            .document(groupId).collection("студенты")
            .document(studentId).addSnapshotListener { value, error ->

                error?.let {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    return@addSnapshotListener
                }

                value?.let {
                    val theor1f = it["theorTrain1First"]
                    val theor1s = it["theorTrain1Second"]
                    binding.theor1.text = theor1f.toString() + "/" + theor1s.toString()
                    val theor2f = it["theorTrain2First"]
                    val theor2s = it["theorTrain2Second"]
                    binding.theor2.text = theor2f.toString() + "/" + theor2s.toString()
                    val practic1f = it["practicTrain1First"]
                    val practic1s = it["practicTrain1Second"]
                    binding.practic1.text = practic1f.toString() + "/" + practic1s.toString()
                    val practic2f = it["practicTrain2First"]
                    val practic2s = it["practicTrain2Second"]
                    binding.practic2.text = practic2f.toString() + "/" + practic2s.toString()
                    val practic3f = it["practicTrain3First"]
                    val practic3s = it["practicTrain3Second"]
                    binding.practic3.text = practic3f.toString() + "/" + practic3s.toString()
                    val general1f = it["generalTrain1First"]
                    val general1s = it["generalTrain1Second"]
                    binding.general1.text = general1f.toString() + "/" + general1s.toString()
                    }
                }


        // Theor1
        binding.btnArrowTheor1.setOnClickListener {
            if (binding.theor1ExpandContent.visibility == View.VISIBLE){
                binding.theor1ExpandContent.visibility = View.GONE
                (it as ImageView).setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_down, null))
                binding.theor1ExpandContent.animate().alpha(0.0f)
            }
            else{
                binding.theor1ExpandContent.visibility = View.VISIBLE
                (it as ImageView).setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_up, null))
                binding.theor1ExpandContent.animate().alpha(1.0f)
            }
        }

        binding.minusFirstTheor1.setOnClickListener {
            var value = binding.theor1FirstValue.text.toString().toInt()
            if (value > 0){
                value -= 1
                binding.theor1FirstValue.text = value.toString()
                saveValueToDB("theorTrain1First", value)
            }
        }

        binding.plusFirstTheor1.setOnClickListener {
            var value = binding.theor1FirstValue.text.toString().toInt()
            if(value < 3){
                value += 1
                binding.theor1FirstValue.text = value.toString()
                saveValueToDB("theorTrain1First", value)
            }
        }

        binding.minusSecondTheor1.setOnClickListener {
            var value = binding.theor1SecondValue.text.toString().toInt()
            if (value > 0){
                value -= 1
                binding.theor1SecondValue.text = value.toString()
                saveValueToDB("theorTrain1Second", value)
            }
        }

        binding.plusSecondTheor1.setOnClickListener {
            var value = binding.theor1SecondValue.text.toString().toInt()
            if(value < 3){
                value += 1
                binding.theor1SecondValue.text = value.toString()
                saveValueToDB("theorTrain1Second", value)
            }
        }

        // Theor 2
        binding.btnArrowTheor2.setOnClickListener {
            if (binding.theor2ExpandContent.visibility == View.VISIBLE){
                binding.theor2ExpandContent.visibility = View.GONE
                (it as ImageView).setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_down, null))
                binding.theor2ExpandContent.animate().alpha(0.0f)
            }
            else{
                binding.theor2ExpandContent.visibility = View.VISIBLE
                (it as ImageView).setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_up, null))
                binding.theor2ExpandContent.animate().alpha(1.0f)
            }
        }

        binding.minusFirstTheor2.setOnClickListener {
            var value = binding.theor2FirstValue.text.toString().toInt()
            if (value > 0){
                value -= 1
                binding.theor2FirstValue.text = value.toString()
                saveValueToDB("theorTrain2First", value)
            }
        }

        binding.plusFirstTheor2.setOnClickListener {
            var value = binding.theor2FirstValue.text.toString().toInt()
            if(value < 3){
                value += 1
                binding.theor2FirstValue.text = value.toString()
                saveValueToDB("theorTrain2First", value)
            }
        }

        binding.minusSecondTheor2.setOnClickListener {
            var value = binding.theor2SecondValue.text.toString().toInt()
            if (value > 0){
                value -= 1
                binding.theor2SecondValue.text = value.toString()
                saveValueToDB("theorTrain2Second", value)
            }
        }

        binding.plusSecondTheor2.setOnClickListener {
            var value = binding.theor2SecondValue.text.toString().toInt()
            if(value < 3){
                value += 1
                binding.theor2SecondValue.text = value.toString()
                saveValueToDB("theorTrain2Second", value)
            }
        }

        // Practic 1
        binding.btnArrowPractic1.setOnClickListener {
            if (binding.practic1ExpandContent.visibility == View.VISIBLE){
                binding.practic1ExpandContent.visibility = View.GONE
                (it as ImageView).setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_down, null))
                binding.practic1ExpandContent.animate().alpha(0.0f)
            }
            else{
                binding.practic1ExpandContent.visibility = View.VISIBLE
                (it as ImageView).setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_up, null))
                binding.practic1ExpandContent.animate().alpha(1.0f)
            }
        }

        binding.minusFirstPractic1.setOnClickListener {
            var value = binding.practic1FirstValue.text.toString().toInt()
            if (value > 0){
                value -= 1
                binding.practic1FirstValue.text = value.toString()
                saveValueToDB("practicTrain1First", value)
            }
        }

        binding.plusFirstPractic1.setOnClickListener {
            var value = binding.practic1FirstValue.text.toString().toInt()
            if(value < 3){
                value += 1
                binding.practic1FirstValue.text = value.toString()
                saveValueToDB("practicTrain1First", value)
            }
        }

        binding.minusSecondPractic1.setOnClickListener {
            var value = binding.practic1SecondValue.text.toString().toInt()
            if (value > 0){
                value -= 1
                binding.practic1SecondValue.text = value.toString()
                saveValueToDB("practicTrain1Second", value)
            }
        }

        binding.plusSecondPractic1.setOnClickListener {
            var value = binding.practic1SecondValue.text.toString().toInt()
            if(value < 3){
                value += 1
                binding.practic1SecondValue.text = value.toString()
                saveValueToDB("practicTrain1Second", value)
            }
        }

        // Practic2

        binding.btnArrowPractic2.setOnClickListener {
            if (binding.practic2ExpandContent.visibility == View.VISIBLE){
                binding.practic2ExpandContent.visibility = View.GONE
                (it as ImageView).setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_down, null))
                binding.practic2ExpandContent.animate().alpha(0.0f)
            }
            else{
                binding.practic2ExpandContent.visibility = View.VISIBLE
                (it as ImageView).setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_up, null))
                binding.practic2ExpandContent.animate().alpha(1.0f)
            }
        }

        binding.minusFirstPractic2.setOnClickListener {
            var value = binding.practic2FirstValue.text.toString().toInt()
            if (value > 0){
                value -= 1
                binding.practic2FirstValue.text = value.toString()
                saveValueToDB("practicTrain2First", value)
            }
        }

        binding.plusFirstPractic2.setOnClickListener {
            var value = binding.practic2FirstValue.text.toString().toInt()
            if(value < 3){
                value += 1
                binding.practic2FirstValue.text = value.toString()
                saveValueToDB("practicTrain2First", value)
            }
        }

        binding.minusSecondPractic2.setOnClickListener {
            var value = binding.practic2SecondValue.text.toString().toInt()
            if (value > 0){
                value -= 1
                binding.practic2SecondValue.text = value.toString()
                saveValueToDB("practicTrain2Second", value)
            }
        }

        binding.plusSecondPractic2.setOnClickListener {
            var value = binding.practic2SecondValue.text.toString().toInt()
            if(value < 3){
                value += 1
                binding.practic2SecondValue.text = value.toString()
                saveValueToDB("practicTrain2Second", value)
            }
        }

        // Practic3

        binding.btnArrowPractic3.setOnClickListener {
            if (binding.practic3ExpandContent.visibility == View.VISIBLE){
                binding.practic3ExpandContent.visibility = View.GONE
                (it as ImageView).setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_down, null))
                binding.practic3ExpandContent.animate().alpha(0.0f)
            }
            else{
                binding.practic3ExpandContent.visibility = View.VISIBLE
                (it as ImageView).setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_up, null))
                binding.practic3ExpandContent.animate().alpha(1.0f)
            }
        }

        binding.minusFirstPractic3.setOnClickListener {
            var value = binding.practic3FirstValue.text.toString().toInt()
            if (value > 0){
                value -= 1
                binding.practic3FirstValue.text = value.toString()
                saveValueToDB("practicTrain3First", value)
            }
        }

        binding.plusFirstPractic3.setOnClickListener {
            var value = binding.practic3FirstValue.text.toString().toInt()
            if(value < 3){
                value += 1
                binding.practic3FirstValue.text = value.toString()
                saveValueToDB("practicTrain3First", value)
            }
        }

        binding.minusSecondPractic3.setOnClickListener {
            var value = binding.practic3SecondValue.text.toString().toInt()
            if (value > 0){
                value -= 1
                binding.practic3SecondValue.text = value.toString()
                saveValueToDB("practicTrain3Second", value)
            }
        }

        binding.plusSecondPractic3.setOnClickListener {
            var value = binding.practic3SecondValue.text.toString().toInt()
            if(value < 3){
                value += 1
                binding.practic3SecondValue.text = value.toString()
                saveValueToDB("practicTrain3Second", value)
            }
        }

        // General1

        binding.btnArrowGeneral1.setOnClickListener {
            if (binding.general1ExpandContent.visibility == View.VISIBLE){
                binding.general1ExpandContent.visibility = View.GONE
                (it as ImageView).setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_down, null))
                binding.general1ExpandContent.animate().alpha(0.0f)
            }
            else{
                binding.general1ExpandContent.visibility = View.VISIBLE
                (it as ImageView).setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_up, null))
                binding.general1ExpandContent.animate().alpha(1.0f)
            }
        }

        binding.minusFirstGeneral1.setOnClickListener {
            var value = binding.general1FirstValue.text.toString().toInt()
            if (value > 0){
                value -= 1
                binding.general1FirstValue.text = value.toString()
                saveValueToDB("generalTrain1First", value)
            }
        }

        binding.plusFirstGeneral1.setOnClickListener {
            var value = binding.general1FirstValue.text.toString().toInt()
            if(value < 3){
                value += 1
                binding.general1FirstValue.text = value.toString()
                saveValueToDB("generalTrain1First", value)
            }
        }

        binding.minusSecondGeneral1.setOnClickListener {
            var value = binding.general1SecondValue.text.toString().toInt()
            if (value > 0){
                value -= 1
                binding.general1SecondValue.text = value.toString()
                saveValueToDB("generalTrain1Second", value)
            }
        }

        binding.plusSecondGeneral1.setOnClickListener {
            var value = binding.general1SecondValue.text.toString().toInt()
            if(value < 3){
                value += 1
                binding.general1SecondValue.text = value.toString()
                saveValueToDB("generalTrain1Second", value)
            }
        }
    }

    private fun saveValueToDB(name: String, value: Int){
        db.collection("Users").document(prefs.myUUId)
            .collection("Секции").document(resources.getString(R.string.diagnosticMap)).collection("группы")
            .document(groupId).collection("студенты")
            .document(studentId)
            .update(name, value)
    }
}