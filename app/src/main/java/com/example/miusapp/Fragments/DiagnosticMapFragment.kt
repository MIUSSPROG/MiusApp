package com.example.miusapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.miusapp.Adapter.DiagnosticMapAdapter
import com.example.miusapp.Model.FirebaseModel
import com.example.miusapp.Model.Group
import com.example.miusapp.Model.SliderItem
import com.example.miusapp.Model.SliderRvItem
import com.example.miusapp.R
import com.example.miusapp.Utils.prefs
import com.example.miusapp.databinding.FragmentDiagnosticMapBinding
import com.example.miusapp.databinding.FragmentUserHomeBinding
import com.google.firebase.firestore.FirebaseFirestore


class DiagnosticMapFragment : Fragment() {

    private lateinit var binding: FragmentDiagnosticMapBinding
    private var bottomSheet = AddGroupFragment()
    private lateinit var db: FirebaseFirestore
    private var groupList: MutableList<Group> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiagnosticMapBinding.inflate(inflater, container, false)

        binding.fabAddGroup.setOnClickListener {
            if(!bottomSheet.isAdded){
                val bundle = Bundle()
//                bundle.putString("itemTitle", getItemByPosition(title, position))
//                bundle.putString("mainTitle", title)
//                bundle.putInt("background", background)
                bottomSheet.arguments = bundle
                bottomSheet.show(childFragmentManager, "")
            }
        }

        binding.rvGroupList.adapter = DiagnosticMapAdapter(groupList, requireContext())
        db = FirebaseFirestore.getInstance()
        db.collection("Users").document(prefs.myUUId)
            .collection("Секции").document(resources.getString(R.string.diagnosticMap)).collection("группы")
            .addSnapshotListener { value, error ->
                error?.let {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    return@addSnapshotListener
                }

                value?.let {
                    groupList.clear()
                    for (document in it.documents) {
                        val newItem = document.toObject(Group::class.java)
                        if (newItem != null) {
                            groupList.add(newItem)
                        }
                    }
                    (binding.rvGroupList.adapter as DiagnosticMapAdapter).notifyDataSetChanged()
                }
            }

        return binding.root
    }

}