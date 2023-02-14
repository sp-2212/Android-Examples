package com.techholding.android.examples.ui.firebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.techholding.android.examples.R
import com.techholding.android.examples.databinding.FragmentFirebaseAuthBinding

class FirebaseAuthFragment : Fragment() {

    private var _binding: FragmentFirebaseAuthBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirebaseAuthBinding.inflate(inflater, container, false)

        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.emailAuthentication.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_firebaseAuthFragment_to_emailFragment)
        }

        binding.phoneAuthentication.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_firebaseAuthFragment_to_phoneFragment)
        }

        binding.googleAuthentication.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_firebaseAuthFragment_to_googleFragment)
        }

        binding.facebookAuthentication.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_firebaseAuthFragment_to_facebookFragment)
        }

        binding.prebuiltAuthentication.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_firebaseAuthFragment_to_preBuiltFragment)
        }
    }
}