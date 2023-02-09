package com.techholding.android.examples.ui.firebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techholding.android.examples.databinding.FragmentFirebaseAuthBinding

class FirebaseAuthFragment : Fragment() {

    private var _binding: FragmentFirebaseAuthBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirebaseAuthBinding.inflate(inflater, container, false)

        binding.emailAuthentication.setOnClickListener {

        }

        binding.phoneAuthentication.setOnClickListener {

        }

        return binding.root
    }
}