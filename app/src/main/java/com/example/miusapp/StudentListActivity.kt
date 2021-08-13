package com.example.miusapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.miusapp.Adapter.DiagnosticMapAdapter
import com.example.miusapp.Adapter.StudentAdapter
import com.example.miusapp.Fragments.AddItemFragment
import com.example.miusapp.Fragments.AddStudentFragment
import com.example.miusapp.Fragments.DiagnosticMapFragment
import com.example.miusapp.Model.Group
import com.example.miusapp.Model.Student
import com.example.miusapp.Utils.prefs
import com.example.miusapp.databinding.ActivityDetailNavigationBinding
import com.example.miusapp.databinding.ActivityStudentListBinding
import com.google.firebase.firestore.FirebaseFirestore

class StudentListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentListBinding
    private var bottomSheet = AddStudentFragment()
    private var studentList: MutableList<Student> = ArrayList()
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        binding = ActivityStudentListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val groupId = intent.extras?.get("id").toString()

        binding.rvStudents.adapter = StudentAdapter(studentList, this)
        db = FirebaseFirestore.getInstance()
        db.collection("Users").document(prefs.myUUId)
            .collection("Секции").document(resources.getString(R.string.diagnosticMap)).collection("группы")
            .document(groupId).collection("студенты")
            .addSnapshotListener { value, error ->
                error?.let {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    return@addSnapshotListener
                }

                value?.let {
                    studentList.clear()
                    for (document in it.documents) {
                        val newItem = document.toObject(Student::class.java)
                        if (newItem != null) {
                            studentList.add(newItem)
                        }
                    }
                    (binding.rvStudents.adapter as StudentAdapter).notifyDataSetChanged()
                }
            }

        binding.fabAddStudent.setOnClickListener {

            if(!bottomSheet.isAdded){
                val bundle = Bundle()
                bundle.putString("id", groupId)
                bottomSheet.arguments = bundle
                bottomSheet.show(supportFragmentManager, "")
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

}