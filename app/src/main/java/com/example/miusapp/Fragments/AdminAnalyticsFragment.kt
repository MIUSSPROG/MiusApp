package com.example.miusapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.miusapp.Adapter.DiagnosticMapAdapter
import com.example.miusapp.Model.Group
import com.example.miusapp.Model.Teacher
import com.example.miusapp.R
import com.example.miusapp.Utils.prefs
import com.example.miusapp.databinding.FragmentAdminAnalyticsBinding
import com.google.firebase.firestore.FirebaseFirestore


class AdminAnalyticsFragment : Fragment() {

    private lateinit var binding: FragmentAdminAnalyticsBinding
    private lateinit var db: FirebaseFirestore
    private var teacherList: MutableList<String> = ArrayList()
    private var groupList: MutableList<Group> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAdminAnalyticsBinding.inflate(inflater, container, false)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, teacherList)
        binding.teacherSearch.setAdapter(adapter)

        db = FirebaseFirestore.getInstance()
        db.collection("Users")
            .addSnapshotListener { value, error ->

                error?.let {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    return@addSnapshotListener
                }

                value?.let {
                    for (document in it.documents) {
                        val teacher = document.toObject(Teacher::class.java)
                        if (teacher != null) {
                            teacherList.add(teacher.name + " : " + teacher.email)
                        }
                    }
                }
            }

        binding.rvAdminGroupList.adapter = DiagnosticMapAdapter(groupList, requireContext())

        binding.teacherSearch.onItemClickListener = OnItemClickListener { parent, arg1, pos, id ->

            var selectedTeacher = binding.teacherSearch.text.toString().split(':')
            var teacherName = selectedTeacher[0].trim()
            var teacherEmail = selectedTeacher[1].trim()
            db.collection("Users")
                .whereEqualTo("name", teacherName)
                .whereEqualTo("email", teacherEmail)
                .get()
                .addOnSuccessListener {  documents ->
                    for (document in documents){
                        val teacherId = document.id
                        prefs.teacherID = teacherId
                        db.collection("Users").document(teacherId)
                            .collection("Секции").document(resources.getString(R.string.diagnosticMap))
                            .collection("группы")
                            .addSnapshotListener { value, error ->

                                error?.let {
                                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                                    return@addSnapshotListener
                                }

                                value?.let {
                                    for (document in it.documents) {
                                        val group = document.toObject(Group::class.java)
                                        if (group != null){
                                            groupList.add(group)
                                        }
                                    }
                                    (binding.rvAdminGroupList.adapter as DiagnosticMapAdapter).notifyDataSetChanged()
                                }
                            }
                    }
                }
        }

        return binding.root
    }

}