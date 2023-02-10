package com.techholding.android.examples.ui.firebase.google

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techholding.android.examples.databinding.FragmentGoogleAuthBinding

class GoogleFragment : Fragment() {

    private var _binding: FragmentGoogleAuthBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoogleAuthBinding.inflate(inflater, container, false)

        return binding.root
    }
}