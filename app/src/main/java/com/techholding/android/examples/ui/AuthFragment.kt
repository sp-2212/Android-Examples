package com.techholding.android.examples.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.techholding.android.examples.R
import com.techholding.android.examples.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAuthBinding.inflate(inflater, container, false)

        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.firebaseAuthentication.setOnClickListener {
            val action = AuthFragmentDirections.actionAuthFragmentToFirebaseAuthFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.awsAuthentication.isEnabled = false
    }
}