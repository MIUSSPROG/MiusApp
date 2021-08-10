package com.example.miusapp.Fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import com.example.miusapp.R
import com.example.miusapp.Utils.prefs
import com.example.miusapp.databinding.FragmentAddItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore

class AddItemFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddItemBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddItemBinding.inflate(inflater, container, false)

        val desc = arguments?.get("itemTitle") as String
        val title = arguments?.get("mainTitle") as String
        val background = arguments?.get("background") as Int
        binding.tvAddItemTitle.text = desc
//        binding.topLine.background = getTitleDrawable(background)

        binding.btnSaveItembyDesc.setOnClickListener {

            db = FirebaseFirestore.getInstance()
            val newQuestionId = db.collection("questions").document().id

            val info: MutableMap<String, Any> = HashMap()
            info["question"] = desc
            info["answer"] = binding.etItemTextToSave.text.toString()
            info["id"] = newQuestionId

            db.collection("Users").document(prefs.myUUId)
                .collection("Секции").document(title).collection("questions")
                .document(newQuestionId)
                .set(info)
                .addOnSuccessListener {
//                    Toast.makeText(requireContext(), "Секция создана!", Toast.LENGTH_SHORT).show()
                    binding.etItemTextToSave.setText("")
                    this.dismiss()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Произошла ошибка", Toast.LENGTH_SHORT).show()
                }

//            db = FirebaseFirestore.getInstance()
//            db.collection("Users").document(prefs.myUUId)
//                .collection("Секции").document(title).collection("questions")
//                .add(info)
//                .addOnSuccessListener {
//                    Toast.makeText(requireContext(), "Секция создана!", Toast.LENGTH_SHORT).show()
//                    binding.etItemTextToSave.setText("")
//                    this.dismiss()
//                }
//                .addOnFailureListener {
//                    Toast.makeText(requireContext(), "Произошла ошибка", Toast.LENGTH_SHORT).show()
//                }

        }

        binding.btnCancelAddItemByDesc.setOnClickListener {
            binding.etItemTextToSave.setText("")
            this.dismiss()
        }

        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog;
    }

    private fun getTitleDrawable(background: Int) : Drawable? {
        return when(background){
            R.drawable.rounded1 -> getDrawable(requireContext(),R.color.color1)
            R.drawable.rounded2 -> getDrawable(requireContext(), R.color.color2)
            R.drawable.rounded3 -> getDrawable(requireContext(), R.color.color3)
            R.drawable.rounded4 -> getDrawable(requireContext(), R.color.color4)
            R.drawable.rounded5 -> getDrawable(requireContext(), R.color.color5)
            R.drawable.rounded6 -> getDrawable(requireContext(), R.color.color6)
            else -> null
        }
    }
}