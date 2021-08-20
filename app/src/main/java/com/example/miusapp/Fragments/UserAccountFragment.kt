package com.example.miusapp.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.miusapp.MainActivity
import com.example.miusapp.R
import com.example.miusapp.Utils.prefs
import com.example.miusapp.databinding.FragmentDiagnosticMapBinding
import com.example.miusapp.databinding.FragmentUserAccountBinding

class UserAccountFragment : Fragment() {

    private lateinit var binding: FragmentUserAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserAccountBinding.inflate(inflater, container, false)

        binding.btnUserExit.setOnClickListener {
            prefs.myUUId = ""
            val intent = Intent(requireContext(), MainActivity::class.java)
            activity?.startActivity(intent)
        }

        return binding.root
    }

}