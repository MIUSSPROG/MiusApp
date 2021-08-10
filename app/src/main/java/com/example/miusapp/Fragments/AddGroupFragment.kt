package com.example.miusapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import com.example.miusapp.R
import com.example.miusapp.Utils.prefs
import com.example.miusapp.databinding.FragmentAddGroupBinding
import com.example.miusapp.databinding.FragmentAddItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*


class AddGroupFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddGroupBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddGroupBinding.inflate(inflater, container, false)

        val datePicker = MaterialDatePicker.Builder.dateRangePicker().setTitleText("Задайте период").build()
        binding.datePickerGroup.setOnClickListener {
            datePicker.show(childFragmentManager, "tag")
        }

        datePicker.addOnPositiveButtonClickListener {
            var timeZone = TimeZone.getDefault()
            var offsetFromUTC = timeZone.getOffset(Date().time) * -1
            var simpleFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            var date1 = Date(it.first + offsetFromUTC)
            var date2 = Date(it.second + offsetFromUTC)
            binding.tvDateRangeGroup.text = simpleFormat.format(date1) + " - " + simpleFormat.format(date2)
        }

        binding.switchModeGroup.setOnCheckedChangeListener{ view, isChecked ->
            if (isChecked){
                binding.switchModeGroup.text = "Очно"
            }
            else{
                binding.switchModeGroup.text = "Дистанционно"
            }
        }

        binding.npAgeFrom.minValue = 6
        binding.npAgeFrom.maxValue = 18
        binding.npAgeFrom.wrapSelectorWheel = false
        binding.npAgeFrom.setOnValueChangedListener { picker, oldVal, newVal ->

        }

        binding.npAgeTo.minValue = 6
        binding.npAgeTo.maxValue = 18
        binding.npAgeTo.wrapSelectorWheel = false
        binding.npAgeTo.setOnValueChangedListener { picker, oldVal, newVal ->

        }

        db = FirebaseFirestore.getInstance()

        binding.btnAddGroup.setOnClickListener {
            val newGroupId = db.collection("группы").document().id
            val group: MutableMap<String, Any> = HashMap()
            group["id"] = newGroupId
            group["ageFrom"] = binding.npAgeFrom.value
            group["ageTo"] = binding.npAgeTo.value
            val dateRangeMas = binding.tvDateRangeGroup.text.split("-")
            group["dateStart"] =dateRangeMas[0].trim()
            group["dateEnd"] = dateRangeMas[1].trim()
            group["name"] = binding.etGroupName.text.toString()
            group["form"] = binding.switchModeGroup.text.toString()

            db.collection("Users").document(prefs.myUUId)
                .collection("Секции").document(resources.getString(R.string.diagnosticMap)).collection("группы")
                .document(newGroupId)
                .set(group)
                .addOnSuccessListener {
                    binding.etGroupName.setText("")
                    this.dismiss()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Произошла ошибка", Toast.LENGTH_SHORT).show()
                }
        }


        return binding.root
    }


}