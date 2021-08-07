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
import com.example.miusapp.databinding.FragmentEditDeleteItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore


class EditDeleteItemFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentEditDeleteItemBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var categoryTitle: String
    private lateinit var answer: String
    private lateinit var title: String
    private lateinit var questionId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditDeleteItemBinding.inflate(inflater, container, false)
        answer = arguments?.get("itemAnswer") as String
        title = arguments?.get("title") as String
        questionId = arguments?.get("itemId") as String
        categoryTitle = arguments?.get("categoryTitle") as String

        binding.tvEditDeleteItemTitle.text = title
        binding.etItemTextToEditDelete.setText(answer)

        db = FirebaseFirestore.getInstance()

        binding.btnEditItem.setOnClickListener {
            db.collection("Users").document(prefs.myUUId)
                .collection("Секции").document(categoryTitle).collection("questions")
                .document(questionId)
                .update("answer", binding.etItemTextToEditDelete.text.toString())
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Изменения сохранены!", Toast.LENGTH_SHORT).show()
                    this.dismiss()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
        }

        binding.btnDeleteItem.setOnClickListener {
            db.collection("Users").document(prefs.myUUId)
                .collection("Секции").document(categoryTitle).collection("questions")
                .document(questionId)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Элемент удален!", Toast.LENGTH_SHORT).show()
                    this.dismiss()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
        }
        return binding.root
    }

}