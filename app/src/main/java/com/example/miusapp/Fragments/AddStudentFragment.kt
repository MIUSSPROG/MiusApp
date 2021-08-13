package com.example.miusapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.miusapp.R
import com.example.miusapp.Utils.prefs
import com.example.miusapp.databinding.FragmentAddItemBinding
import com.example.miusapp.databinding.FragmentAddStudentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore

class AddStudentFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddStudentBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddStudentBinding.inflate(inflater, container, false)
        val groupId = arguments?.get("id") as String

        db = FirebaseFirestore.getInstance()

        binding.btnAddStudentToGroup.setOnClickListener {
            val newStudentId = db.collection("students").document().id
            val info: MutableMap<String, Any> = HashMap()
            info["id"] = newStudentId
            info["name"] = binding.etStudentFio.text.toString()
            info["age"] = binding.etStudentAge.text.toString().toInt()
            info["percentComplete"] = 0

            db.collection("Users").document(prefs.myUUId)
                .collection("Секции").document(resources.getString(R.string.diagnosticMap)).collection("группы")
                .document(groupId).collection("студенты")
                .document(newStudentId)
                .set(info)
                .addOnSuccessListener {

                    db.collection("Users").document(prefs.myUUId)
                        .collection("Секции").document(resources.getString(R.string.diagnosticMap)).collection("группы")
                        .document(groupId).collection("студенты")
                        .get()
                        .addOnCompleteListener{ task ->
                            if (task.isSuccessful){
                                val count = task.result?.size()
                                db.collection("Users").document(prefs.myUUId)
                                    .collection("Секции").document(resources.getString(R.string.diagnosticMap)).collection("группы")
                                    .document(groupId).update("studentsCount", count)
                                binding.etStudentFio.setText("")
                                binding.etStudentAge.setText("")
                                this.dismiss()
                            }
                            else{
                                Toast.makeText(requireContext(), "Упс!...Произошла ошибка", Toast.LENGTH_LONG).show()
                                binding.etStudentFio.setText("")
                                binding.etStudentAge.setText("")
                                this.dismiss()
                            }
                        }

                }
        }


        return binding.root
    }
}