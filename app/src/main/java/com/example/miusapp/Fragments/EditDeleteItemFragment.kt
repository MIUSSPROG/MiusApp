package com.example.miusapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.miusapp.R
import com.example.miusapp.Utils.prefs
import com.example.miusapp.databinding.FragmentAddItemBinding
import com.example.miusapp.databinding.FragmentEditDeleteItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore


class EditDeleteItemFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentEditDeleteItemBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditDeleteItemBinding.inflate(inflater, container, false)
        val desc = arguments?.get("itemTitle") as String
        val title = arguments?.get("mainTitle") as String

        binding.tvEditDeleteItemTitle.text = title
        binding.etItemTextToEditDelete.setText(desc)

        db = FirebaseFirestore.getInstance()

        binding.btnEditItem.setOnClickListener {
            db.collection("Users").document(prefs.myUUId)
                .collection("Секции").document(title).collection("questions")
        }

        binding.btnDeleteItem.setOnClickListener {

        }
        return binding.root
    }

}