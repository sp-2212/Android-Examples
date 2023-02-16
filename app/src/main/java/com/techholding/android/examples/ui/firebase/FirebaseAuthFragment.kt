package com.techholding.android.examples.ui.firebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
            findNavController().navigate(
                FirebaseAuthFragmentDirections.actionFirebaseAuthFragmentToEmailFragment()
            )
        }

        binding.phoneAuthentication.setOnClickListener {

            findNavController().navigate(
                FirebaseAuthFragmentDirections.actionFirebaseAuthFragmentToPhoneFragment()
            )
        }

        binding.googleAuthentication.setOnClickListener {

            findNavController().navigate(
                FirebaseAuthFragmentDirections.actionFirebaseAuthFragmentToGoogleFragment()
            )
        }

        binding.facebookAuthentication.setOnClickListener {

            findNavController().navigate(
                R.id.action_firebaseAuthFragment_to_facebookFragment
            )
        }


        binding.prebuiltAuthentication.setOnClickListener {

            findNavController().navigate(
                R.id.action_firebaseAuthFragment_to_preBuiltFragment
            )
        }
    }
}